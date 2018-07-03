import { Injectable, Inject } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { getManager, getRepository } from 'typeorm';
import { Event } from './event.entity';
import * as google from './calendar/google.calendar';

@Injectable()
export class EventService {
  constructor(
      @InjectRepository(Event)
      private readonly eventRepository: Repository<Event>,
      ) {}

  async createEvent(event: Event){
    google.run(event, function(response) {
      const eventOfDatabase = event.content;
      eventOfDatabase.id_event = response.id;
      console.log(response.id);
      getRepository(Event).save(eventOfDatabase);
    })
  }

}
