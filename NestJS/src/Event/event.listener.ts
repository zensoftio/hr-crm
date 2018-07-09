import { EventService } from './event.service';
import { Controller } from '@nestjs/common';
import * as connection from 'Rabbit';
import * as Amqp from "amqp-ts";
const exchange = connection.default.declareExchange("js-backend", 'direct', { durable: false });
const queue = connection.default.declareQueue('event', {durable: true});

@Controller('event')
export class EventController {
  constructor(private readonly eventService: EventService) {
       this.initRabbitMQ();
  }

  public async getDataFromService(message: any) {
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
    this.sendResponse(this.msg);
  }
  private sendResponse(res) {
    connection.default.declareQueue("event-response", {durable: false})
    connection.default.completeConfiguration().then(() => {
      var msg2 = new Amqp.Message(res);
      exchange.send(msg2);
      console.log(' [x] Sent message-response  \'' + msg2.getContent() + '\'');
    });
  }
  private initRabbitMQ(callback){
    queue.bind(exchange, 'black');
      queue.activateConsumer((message) => {
        var data = JSON.parse(message.getContent());
        console.log("GET");
        console.log(data);
        console.log("GET");
        this.getDataFromService(data);
    }, {noAck: true})
  }

}
