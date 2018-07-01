import { EventService } from './event.service';
import { Controller } from '@nestjs/common';

@Controller('event')
export class EventController {
  constructor(private readonly eventService: EventService) {
    var exchange =  connection.declareExchange("ExchangeName", 'direct');
    var queue = connection.declareQueue("event", {durable: true});
    queue.bind(exchange, 'black');
    queue.activateConsumer(async (message) => {
         let json = JSON.parse(message.getContent());
         await run(json, this.eventService, function(err, response){
           let msg = {
             "title": json.title,
             "body": {}
           }
           msg.body = response;
           console.log("Message received: ");
           console.log(msg);
      });
    }, {noAck: true});
  }
}
async function run(json, eventService, callback){
  if(json.title == "create"){
    await eventService.createEvent(json, function(err, response){
      if(err){
        callback(err, '');
      }
      else{
        callback('', response);
      }
    });
  }
  else if(json.title == "update"){
    await eventService.updateEvent(json, function(err, response){
      if(err){
        callback(err, '');
      }
      else{
        callback('', response);
      }
    });
  }
  else if(json.title == "getlist"){
    await eventService.getList(function(err, response){
      if(err){
        callback(err, '');
      }
      else{
        callback('', response);
      }
    });
  }
  else if(json.title == "getone"){
    await eventService.getOne(json, function(err, response){
      if(err){
        callback(err, '');
      }
      else{
        callback('', response);
      }
    });
  }
  else if(json.title == "remove"){
    await eventService.removeEvent(json, function(err, response){
      if(err){
        callback(err, '');
      }
      else{
        callback('', response);
      }
    });
  }
  else{
    callback("Send back message about incorrect title!", '');
  }
}
