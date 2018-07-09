import * as qs from '../gmail_api/gmailapi'
import {MessageService} from './message.service'
import { Controller } from '@nestjs/common';
import * as Amqp from "amqp-ts";
import { RecipientService } from 'Recipients/recipient.service';
import * as connection from 'Rabbit';

const exchange = connection.default.declareExchange("js-backend", 'direct', { durable: false });
const queue = connection.default.declareQueue('message', {durable: false});

@Controller('messages')
export class MessageListener {

    constructor(private readonly messageService: MessageService,
    private readonly recipientService: RecipientService){
      this.listenQueue();
  }

  private listenQueue() {
    queue.bind(exchange, 'message');
      queue.activateConsumer((message) => {
        var msg = message.getContent()
        console.log(msg)
        var data = JSON.parse(msg)
        this.takeAction(data);
    }, {noAck: true})
  }

  private takeAction(msg) {
     let res;
    switch(msg.task){
      case "send":
        res = this.sendEmail(msg)
        break;
      case "get":
        res = this.getMessages(msg)
        break;
    }
    return res
  }

  async getMessages(data){
    const msgs = await this.messageService.findByRecipient(data.recipient)
    const response = JSON.stringify(msgs)
   this.sendResponse(response)
    return response
  }

  sendResponse(res) {
    connection.default.declareQueue("message-response")
    connection.default.completeConfiguration().then(() => {
      var msg2 = new Amqp.Message(res);
      exchange.send(msg2);
      console.log(' [x] Sent message-response  \'' + msg2.getContent() + '\'');
    });
  }

  async sendEmail(data){
      const results = await Promise.all(data.recipients.map((element) => qs.sendMessageH(data, element)));
      try {
          await this.saveRecipientsToDb(data)
      }catch (err) {
          console.log(err)
      }
      this.sendResponse(results)
  }

  async saveRecipientsToDb(data){
    const msgId = await this.messageService.create(data)
    data.recipients.map((rec) => rec.message = msgId)
    return this.recipientService.create(data.recipients)
  }
}
