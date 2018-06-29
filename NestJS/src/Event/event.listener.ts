import { EventService } from './event.service';

export class EventListener {
  private readonly eventService: EventService = new EventService();
  constructor() {}

  sendMessage(msg){
    this.msg = msg;
  }

  doEvent(){
    this.eventService.createEvent(this.msg.content);
  }

}
