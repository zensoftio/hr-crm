import * as qs from '../gmail_api/gmailapi'
import {MessageService} from './message.service'
import { Controller } from '@nestjs/common';
import { RabbitMQClient } from 'rabbitmq-client';

@Controller('message')
export class MessageController {
  // constructor(private readonly messageService: MessageService){}

  public sendMessage(data) {
    console.log(data)
    qs.sendMessageH(data)
   
  }
}

