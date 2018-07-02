import * as qs from '../gmail_api/gmailapi'
import {MessageService} from './message.service'
import { Controller } from '@nestjs/common';
import * as Amqp from "amqp-ts";
import { RecipientService } from 'Recipients/recipient.service';

var connection = new Amqp.Connection("amqp://localhost");
var exchange = connection.declareExchange("test3", 'direct', { durable: false });
var queue = connection.declareQueue('');

@Controller('message')
export class MessageListener {
    constructor(private readonly messageService: MessageService,
    private readonly recipientService: RecipientService){
      queue.bind(exchange, 'message');
      queue.activateConsumer((message) => {
        var msg = message.getContent()
        var data = JSON.parse(msg)
        qs.sendMessageH(data)
        console.log("Message is send!")
        getMsgId(data)

        async function getMsgId(data){
          const a = await messageService.create(data)
          data.recipients.map((rec) => rec.message = a)
          
          return recipientService.create(data.recipients)
        }

    }, {noAck: true})

  }
}
