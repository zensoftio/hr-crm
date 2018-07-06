import { EventService } from './event.service';
import { Controller } from '@nestjs/common';
import deasyncPromise from 'deasync-promise';
import * as channel from 'Rabbit';


@Controller('event')
export class EventController {
  constructor(private readonly eventService: EventService) {
       this.initRabbitMQ(async (msg) => {
            var jsonObj = JSON.parse(msg.content.toString());
            const result = await this.getDataFromService(jsonObj);
            console.log(result);
            channel.default.sendToQueue(msg.properties.replyTo, new Buffer.from(JSON.stringify(result)));
            channel.default.ack(msg);
       });
  }

  public async getDataFromService(message: any): Promise<any> {
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
    channel.default.assertQueue('event', {durable: true});
    channel.default.prefetch(1);
    console.log(' [x] Awaiting RPC requests');
    channel.default.consume('event', async function reply(msg) {
      callback(msg);
    });
  }

}
