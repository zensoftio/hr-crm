import { Controller } from '@nestjs/common';
import * as Amqp from "amqp-ts";
import { TemplateService } from './template.service';

const connection = new Amqp.Connection("amqp://localhost");
const exchange = connection.declareExchange("exchangeForTemplate", 'direct', { durable: false });
const queue = connection.declareQueue('template');
@Controller('template')
export class TemplateListener {
    constructor(private readonly templateService: TemplateService){
      this.listenQueue();
    }

    public distributionTasks(task){
      const obj = {
        CREATE: this.createTemplate,
        UPDATE: this.updateTemplate,
        DELETE: this.deleteTemplate,
        FIND_ALL: this.findAllTemplate,
        FIND_ONE: this.findOneTemplate,
      };
      if (obj[task.title]) {
        obj[task.title](task)
      } else {
        this.sendMessage("SOMETHING IS WRONG");
      }
    }

    async findAllTemplate = (template) => {
      this.templateService.findAll()
      .then( (temp) => { this.sendMessage(temp) })
      .catch( (err) => { this.sendMessage("NOT FOUND") })
    }

    async findOneTemplate = (template) => {
      this.templateService.findOne(template.id)
      .then( (temp) => { this.sendMessage(temp) })
      .catch( (err) => { this.sendMessage("NOT FOUND") })
    }

    async deleteTemplate = (template) => {
      this.templateService.deleteOne(template.id)
      .then( (temp) => { this.sendMessage("DELETED") })
      .catch( (err) => { this.sendMessage("NOT DELETED") })
    }

    async updateTemplate = (template) => {
      this.templateService.update(template.id,template)
      .then( (temp) => { this.sendMessage(temp) })
      .catch( (err) => { this.sendMessage("NOT UPDATED") })
    }

    async createTemplate = (template) => {
      this.templateService.create(template)
      .then( (temp) => { this.sendMessage(temp) })
      .catch( (err) => { this.sendMessage("NOT CREATED") })
    }

    private async sendMessage(msg){
      var sendQueue = connection.declareQueue("template2")
      connection.completeConfiguration().then(() => {
          var msg2 = new Amqp.Message(msg);
          exchange.send(msg2);
      });
    }

    private async listenQueue(){
      queue.bind(exchange, 'template');
      queue.activateConsumer((message) => {
        var msg = message.getContent()

        msg = JSON.parse(msg)
        this.distributionTasks(msg);

        }, {noAck: true})
    }

}
