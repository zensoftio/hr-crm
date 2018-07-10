import { Controller } from '@nestjs/common';
import * as Amqp from "amqp-ts";
import { InboxService } from './inbox.service';
import { Inbox } from './inbox.interface';

const connection = new Amqp.Connection("amqp://localhost");
const exchange = connection.declareExchange("exchangeForTemplate", 'direct', { durable: false });
const queue = connection.declareQueue('inboxMessages');

@Controller('inbox')
export class InboxListener {
    constructor(private readonly inboxService: InboxService){
      this.listenQueue();
    }

    public distributionTasks(task: any):void{
      const obj = {
        UPDATE: this.updateInboxList,
        GET_ONE: this.getOneMessage,
      };
      if (obj[task.title]) {
        obj[task.title](task);
      } else {
        this.sendMessage("TASK NOT FOUND");
      }
    }

    async updateInboxList = (message: any):any => {
      this.inboxService.getMessages(message)
      .then( (messages)  => { this.sendMessage(messages) })
      .catch( (err) => { this.sendMessage("CAN'T UPDATE INBOX LIST") })
    }

    async getOneMessage = (message: any):any => {
      this.inboxService.getOneMessage(message)
      .then( (message)  => { this.sendMessage(message) })
      .catch( (err) => { this.sendMessage("CAN'T GET ONE MESSAGE") })
    }

    private async sendMessage(msg: any):any {
      var sendQueue = connection.declareQueue("inboxMessages2")
      connection.completeConfiguration().then(() => {
          var msg2 = new Amqp.Message(msg);
          exchange.send(msg2);
      });
    }

    private async listenQueue():void {
      queue.bind(exchange, 'inboxMessages');
      queue.activateConsumer((message) => {
        var msg = message.getContent()
        msg = JSON.parse(msg)
        this.distributionTasks(msg);
        }, {noAck: true})
    }

}
