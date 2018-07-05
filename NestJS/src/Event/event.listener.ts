import { EventService } from './event.service';
import { Controller } from '@nestjs/common';
import deasyncPromise from 'deasync-promise';
import * as connection from 'Rabbit';


@Controller('event')
export class EventController {
  constructor(private readonly eventService: EventService) {
       this.queue = connection.default.declareQueue("event", {durable: true});
       this.initRabbitMQ(async (msg) => {
         const json = JSON.parse(msg.getContent());
         const result = await this.getActionFromMessage(json);
         msg._channel.sendToQueue(
                                  msg.properties.replyTo,
                                  new Buffer.from(JSON.stringify(result))
                                 );
     });
  }

  public async getActionFromMessage(message: any): Promise<any> {
    try{
      this.msg = {
        'title': message.title,
        'body': message.body
      }
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
    }
    catch(err){
      this.msg.body = err;
    }
    return this.msg;
  }

  private initRabbitMQ(callback){
    this.queue.activateConsumer((msg) => {
      callback(msg);
    },{noAck: true});
  }
}
