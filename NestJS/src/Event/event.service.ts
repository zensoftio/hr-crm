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

  async createEvent(event: Event, callback){
    google.run(event, function(err, response) {
      if(err){
        callback(err, '');
      }
      else{
        const eventOfDatabase = event.body;
        eventOfDatabase.id_event = response.id;
        getRepository(Event).insert(eventOfDatabase);
        callback('', eventOfDatabase);
      }
    });
  }

  async getList(callback){
    const events = await getRepository(Event).find({});
    callback('', events);
  }
  async getOne(event: Event, callback) {
    const eventOfDatabase = await getRepository(Event).findOne({id_event: event.body.id_event});
    if(eventOfDatabase){
      callback('', eventOfDatabase);
    }
    else{
      callback(`cannot find 'event of database' because of incorrect 'id_event'`, '');
    }
}
  async updateEvent(event: Event, callback){
    const eventOfDatabase = await getRepository(Event).findOne({id_event: event.body.id_event});
    event.body.id = eventOfDatabase.id;
    event.body.id_event = eventOfDatabase.id_event;
    google.run(event, function(err, response){
      if(err){
        callback(err, '');
      }
      else{
        getRepository(Event).save(event.body);
        callback('', event.body);
      }
    });
  }
  async removeEvent(event: Event, callback){
    const eventOfDatabase = await getRepository(Event).findOne({id_event: event.body.id_event});
    event.body = eventOfDatabase;
    google.run(event, function(err, response){
      if(err){
        callback(err, '');
      }
      else{
        getRepository(Event).delete(eventOfDatabase);
        callback('', response);
      }
    });
  }
}
