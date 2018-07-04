import { EventService } from './event.service';
import { Controller } from '@nestjs/common';

@Controller('event')
export class EventController {
  constructor(private readonly eventService: EventService) {

     var exchange =  connection.declareExchange("hello", 'direct');
     var queue = connection.declareQueue("event", {durable: true});
     queue.bind(exchange, 'black');
     queue.activateConsumer((message) => {
         console.log("Message received: " + JSON.parse(message.getContent()));
     }, {noAck: true});
  }


}
