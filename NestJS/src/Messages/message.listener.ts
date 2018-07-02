import * as qs from '../gmail_api/gmailapi'
import {MessageService} from './message.service'
import { Controller } from '@nestjs/common';
import * as Amqp from "amqp-ts";
import { RecipientService } from 'Recipients/recipient.service';

const connection = new Amqp.Connection("amqp://localhost");
const exchange = connection.declareExchange("js-backend", 'direct', { durable: false });
const queue = connection.declareQueue('messages');

@Controller('message')
export class MessageListener {
    constructor(private readonly messageService: MessageService,
    private readonly recipientService: RecipientService){
      this.listenQueue();
  }

  private listenQueue() {
    queue.bind(exchange, 'message');
      queue.activateConsumer((message) => {
        var msg = message.getContent()
        var data = JSON.parse(msg)
        this.takeAction(data);
    }, {noAck: true})
  }

  private takeAction(msg) {
    switch(msg.task){
      case "send":
        this.sendEmail(msg)
        break;
      case "get":
        const res = this.getMessages(msg)
        break;

    }
  }

  async getMessages(data){
    const msgs = await this.messageService.findByRecipient(data.recipient)
    const response = JSON.stringify(msgs)
    this.sendResponse(response)
    return response
  }

  sendResponse(res) {
    const queueRes = connection.declareQueue("message-response")
    connection.completeConfiguration().then(() => {
      var msg2 = new Amqp.Message(res);
      exchange.send(msg2);
      console.log(' [x] Sent message-response  \'' + msg2.getContent() + '\'');
    });
  }

  sendEmail(data){
    let i;
    for(i = 0; i < data.recipients.length; i++) {
      qs.sendMessageH(data, data.recipients[i])
    }
    console.log("Message is send!")
    this.getMsgId(data)
  }

  async getMsgId(data){
    const a = await this.messageService.create(data)
    data.recipients.map((rec) => rec.message = a)
    return this.recipientService.create(data.recipients)
  }
}
