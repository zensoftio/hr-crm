import { Controller } from '@nestjs/common';
import * as Amqp from "amqp-ts";
import { InboxService } from './inbox.service';

const connection = new Amqp.Connection("amqp://localhost");
const exchange = connection.declareExchange("exchangeForTemplate", 'direct', { durable: false });
const queue = connection.declareQueue('inboxMessages');

@Controller('inbox')
export class InboxListener {
    constructor(private readonly inboxService: InboxService){
      this.listenQueue();
    }

    public distributionTasks(task){
      const obj = {
        UPDATE: this.updateInboxList,
        GET_ONE: this.getOneMessage,
      };
      if (obj[task.title]) {
        obj[task.title](task);
      } else {
        this.sendMessage("SOMETHING IS WRONG");
      }
    }

    async updateInboxList = (message) => {
      this.inboxService.getMessages(message)
      .then( (messages)  => { this.sendMessage(messages) })
      .catch( (err) => { this.sendMessage("SOMETHING IS WRONG") })
    }

    async getOneMessage = (message) => {
      this.inboxService.getOneMessage(message)
      .then( (message)  => { this.sendMessage(message) })
      .catch( (err) => { this.sendMessage("SOMETHING IS WRONG") })
    }

    private async sendMessage(msg){
      var sendQueue = connection.declareQueue("inboxMessages2")
      connection.completeConfiguration().then(() => {
          var msg2 = new Amqp.Message(msg);
          exchange.send(msg2);
      });
    }

    private async listenQueue(){
      queue.bind(exchange, 'inboxMessages');
      queue.activateConsumer((message) => {
        var msg = message.getContent()
        msg = JSON.parse(msg)
        this.distributionTasks(msg);
        }, {noAck: true})
    }

}
