import { Controller } from '@nestjs/common';
import * as Amqp from "amqp-ts";
import { TemplateService } from './template.service';
import * as connection from 'Rabbit';

const exchange = connection.default.declareExchange("js-backend", 'direct', { durable: false });
const queue = connection.default.declareQueue('template',{durable: false});

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

    async findAllTemplate = async(template) => {
      try {
        const result = await this.templateService.findAll();
        this.sendMessage(result);
      }catch(err) {
        this.sendMessage(err);
        throw err;
      }
    }

     findOneTemplate = async(template) => {
      try {
        const result = await this.templateService.findOne(template.id);
        this.sendMessage(result);
      }catch(err) {
        this.sendMessage(err);
        throw err;
      }
    }

     deleteTemplate = async(template) => {
      try {
        const result = await this.templateService.deleteOne(template.id);
        this.sendMessage(result);
      }catch(err) {
        this.sendMessage(err);
        throw err;
      }
    }

     updateTemplate = async(template) => {
      try {
        const result = await this.templateService.update(template.id,template);
        this.sendMessage(result);
      }catch(err) {
        this.sendMessage(err);
        throw err;
      }
    }

     createTemplate = async(template) => {
      try {
        const result = await this.templateService.update(template);
        this.sendMessage(result);
      }catch(err) {
        this.sendMessage(err);
        throw err;
      }
    }

    private async sendMessage(msg){
      connection.default.completeConfiguration().then(() => {
          var msg2 = new Amqp.Message(msg);
          exchange.send(msg2,'template2',{durable:false});
      });
    }

    private async listenQueue(){
      queue.bind(exchange, queue);
      queue.activateConsumer((message) => {
        var msg = message.getContent()

        msg = JSON.parse(msg)
        this.distributionTasks(msg);

        }, {noAck: true})
    }

}
