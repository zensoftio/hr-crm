import { Injectable, Inject } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { getManager, getRepository } from 'typeorm';
import { Event } from './event.entity';
import * as google from './calendar/google.calendar';

@Injectable()
export class EventService {
   constructor() {}

  async createEvent(event: Event){
    google.run(event, function(response) {
      const eventOfDatabase = event.content;
      eventOfDatabase.id_event = response.id;
      console.log(response.id);
      getRepository(Event).save(eventOfDatabase);
    })
  }

  // async updateEvent(event: Event){
  //   await this.eventRepository.save(event);
  // }
  //
  // async getEvent(event: Event): Promise<Event> {
  //   return await this.eventRepository.findOne({id: event.id});
  // }
  //
  // async listEvent(): Promise<Event[]> {
  //   return await this.eventRepository.find();
  // }
  //
  // async deleteEvent(event: Event){
  //   const event = await this.eventRepository.findOne({id: event.id});
  //   await this.eventRepository.remove(event);
  // }

}
