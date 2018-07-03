import { EventService } from './event.service';
import { Controller } from '@nestjs/common';
import * as Amqp from "amqp-ts";
import deasyncPromise from 'deasync-promise';

@Controller('event')
export class EventController {
  constructor(private readonly eventService: EventService) {
      this.queue = connection.declareQueue("event", {durable: true});
      this.queue.activateConsumer(this._onMessage, {noAck: true});
      this.msg = {
        "title": "",
        "body": {}
      }
  }

  public async getActionFromMessage(message): Promise<any> {
    this.msg.title = await message.title;
    this.msg.body = await message.body;
    if(this.msg.title === 'create'){
       this.msg.body = await this.eventService.createEvent(this.msg);
    }
    else if(this.msg.title === 'update'){
       this.msg.body = await this.eventService.updateEvent(this.msg);
    }
    else if(this.msg.title === 'getone'){
      this.msg.body = await this.eventService.getOne(this.msg);
    }
    else if(this.msg.title === 'getlist'){
       this.msg.body = await this.eventService.getList();
    }
    else if(this.msg.title === 'remove'){
       this.msg.body = await this.eventService.removeEvent(this.msg);
    }
    else{
       this.msg.body = await 'Incorrect title';
    }
    return this.msg;
  }

   private _onMessage = (message) => {
     const json = JSON.parse(message.getContent());
     let result = this.getActionFromMessage(json);
     let answer;
     setTimeout(async function(){
       answer = await result;
     }, 0);
     while(answer === undefined){
       require('deasync').sleep(100);
     }
     console.log(answer)
     return answer;
   }
}
