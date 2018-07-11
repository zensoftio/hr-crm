import { EventService } from './event.service';
import { Controller } from '@nestjs/common';
import deasyncPromise from 'deasync-promise';
import * as Amqp from 'amqp-ts'
import * as connection from 'Rabbit';
const exchange = connection.default.declareExchange("js-backend", 'direct', { durable: false });
const queue = connection.default.declareQueue('event', {durable: false});

@Controller('event')
export class EventController {
  constructor(private readonly eventService: EventService) {
       this.initRabbitMQ();
  }

  public async getDataFromService(message: any) {
    let msg = {
      'title': message.title,
      'body': message.body
    }
    try{
      if(msg.title === 'create'){
        msg.body = await this.eventService.createEvent(msg);
      }
      else if(msg.title === 'update'){
         msg.body = await this.eventService.updateEvent(msg);
      }
      else if(msg.title === 'getone'){
        msg.body = await this.eventService.getOne(msg);
      }
      else if(msg.title === 'getlist'){
         msg.body = await this.eventService.getList();
      }
      else if(msg.title === 'remove'){
         msg.body = await this.eventService.removeEvent(msg);
      }
      else{
         msg.body = await 'Incorrect title';
      }
    }
    catch(err){
      msg.body = err;
    }
    this.sendResponse(msg);
  }
  private async sendResponse(res) {
    connection.default.declareQueue("event-response", {durable: false})
    connection.default.completeConfiguration().then(() => {
      const msg2 = new Amqp.Message(JSON.stringify(res));
      exchange.send(msg2, 'event-response');
      console.log(' [x] Sent event-response  \'' + msg2.getContent() + '\'');
    });
  }

  private initRabbitMQ(){
    queue.bind(exchange, 'event');
    queue.activateConsumer((message) => {
        const data = JSON.parse(message.getContent());
        console.log("GET");
        console.log(data)
        console.log("GET");
        this.getDataFromService(data);
    }, {noAck: true})
  }

}
