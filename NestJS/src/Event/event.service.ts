import { Injectable, Inject } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { getManager, getRepository, Repository } from 'typeorm';
import { Event } from './event.entity';
import * as google from './calendar/google.calendar';
import * as channel from 'Rabbit';
import * as sms from './phone_notification/sms';
@Injectable()
export class EventService {
  constructor(
      @InjectRepository(Event)
      private readonly eventRepository: Repository<Event>,
      ) {}

  async createEvent(event: any): Promise<any>{
    return new Promise(function(resolve, reject){
      google.run(event, function(err, response) {
        let eventOfDatabase: any = event.body;
        if(!err){
          eventOfDatabase.id_event = response.id;
          getRepository(Event).insert(eventOfDatabase);
          (sms.send(eventOfDatabase, 'create')) ? true : reject('Cannot send for all phone_numbers');
        }
        err ? reject(err) : resolve(eventOfDatabase);
      });
    })
  }

  async getList(): Promise<any[]>{
    let eventOfDatabase: any = await getRepository(Event).find({});

    if(!eventOfDatabase) eventOfDatabase = "There is no events!";

    return eventOfDatabase;
  }

  async getOne(event: any): Promise<any>{
    let eventOfDatabase: any = await getRepository(Event).findOne({id_event: event.body.id_event});
    if(!eventOfDatabase) eventOfDatabase = "whether invalid id_event or no such event";
    return eventOfDatabase;
  }

  async updateEvent(event: any): Promise<any>{
    return new Promise(async (resolve, reject) => {
        const eventOfDatabase: any = await getRepository(Event).findOne({id_event: event.body.id_event});
        if(!eventOfDatabase){
          reject("whether invalid id_event or no such event!");
        }
        else{
          event.body.id = eventOfDatabase.id;
          event.body.id_event = eventOfDatabase.id_event;
          google.run(event, function(err, response){
            if(!err){
              getRepository(Event).save(event.body);
              (sms.send(event.body, 'update')) ? true : reject('Cannot send for all phone_numbers');
            }
            err ? reject(err) : resolve(event.body);
          });
        }
    });
  }
  async removeEvent(event: any): Promise<any> {
    return new Promise(async (resolve, reject) =>{

      const eventOfDatabase: any = await getRepository(Event).findOne({id_event: event.body.id_event});
      if(!eventOfDatabase){
        resolve("whether invalid id_event or no such event!");
      }
      else{
        const eventForSms: any = event.body;
        event.body = eventOfDatabase;
        google.run(event, function(err, response){
          getRepository(Event).delete(eventOfDatabase);
          (sms.send(eventForSms, 'delete')) ? true : reject('Cannot send for all phone_numbers');
          err ? reject(err) : resolve(response);
        });
      }
    });
  }
}
