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

  async createEvent(event: Event): Promise<Event>{
    return new Promise(function(resolve, reject){
      google.run(event, function(err, response) {
        let eventOfDatabase = event.body;
        if(!err){
          eventOfDatabase.id_event = response.id;
          getRepository(Event).insert(eventOfDatabase);
        }
        err ? reject(err) : resolve(eventOfDatabase);
      });
    })
  }

  async getList(): Promise<Event[]>{
    let eventOfDatabase = await getRepository(Event).find({});

    if(!eventOfDatabase) eventOfDatabase = "There is no events!";

    return eventOfDatabase;
  }

  async getOne(event: Event): Promise<Event>{
    let eventOfDatabase = await getRepository(Event).findOne({id_event: event.body.id_event});
    if(!eventOfDatabase) eventOfDatabase = "whether invalid id_event or no such event";
    return eventOfDatabase;
  }

  async updateEvent(event: Event): Promise<Event>{
    return new Promise(async (resolve, reject) => {
        const eventOfDatabase = await getRepository(Event).findOne({id_event: event.body.id_event});
        if(!eventOfDatabase){
          reject("whether invalid id_event or no such event!");
        }
        else{
          event.body.id = eventOfDatabase.id;
          event.body.id_event = eventOfDatabase.id_event;
          google.run(event, function(err, response){
            if(!err){
              getRepository(Event).save(event.body);
            }
            err ? reject(err) : resolve(event.body);
          });
        }
    });
  }
  async removeEvent(event: Event): string{
    return new Promise(async (resolve, reject) =>{
      const eventOfDatabase = await getRepository(Event).findOne({id_event: event.body.id_event});
      if(!eventOfDatabase){
        resolve("whether invalid id_event or no such event!");
      }
      else{
        event.body = eventOfDatabase;
        google.run(event, function(err, response){
          getRepository(Event).delete(eventOfDatabase);
          err ? reject(err) : resolve(response);
        });
      }
    });
  }
}
