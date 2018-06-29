import { EventService } from './event.service';

export class EventListener {
  private readonly eventService: EventService = new EventService();
  constructor() {}

  sendMessage(msg){
    this.msg = msg;
  }

  doEvent(){
    if(this.msg.description == "create"){
      this.eventService.createEvent(this.msg);
    }

  }

}
