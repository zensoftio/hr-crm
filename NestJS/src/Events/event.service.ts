import { Injectable, Inject } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { Event } from './event.entity';

@Injectable()
export class EventService {
  constructor(
    @InjectRepository(Event)
    private readonly eventRepository: Repository<Event>,
  ) {}

  async createEvent(event: Event): Promise<Event> {
    return await this.eventRepository.save(event);
  }
  async findAll(): Promise<Event[]> {
    return await this.eventRepository.find();
  }
  async remove(id: number){
    const event = await this.eventRepository.findOne(id);
    await this.eventRepository.remove(id);
  }
}
