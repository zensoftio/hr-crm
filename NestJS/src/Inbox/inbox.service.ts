import { Injectable } from '@nestjs/common';
import * as gmail from '../gmail_api/gmailapi'
import { InjectRepository, createQueryBuilder } from '@nestjs/typeorm';
import { Repository, createQueryBuilder } from 'typeorm';
import { Inboxes } from './inbox.entity';

@Injectable()
export class InboxService {
  constructor(
    @InjectRepository(Inboxes)
    private readonly inboxRepository: Repository<Inboxes>
    ){}

  async update(date: Inboxes): Promise<Inboxes> {
    try {
      const toDate = {
        last_update: date
      }
      const toUpdate = await this.inboxRepository.findOne();
      await this.inboxRepository.update(toUpdate.id,toDate);
      return toUpdate;
    }catch (e){
      console.log(e)
    }

  }

  async getMessages(message){
    const date = await this.update(message.date);
    return date;
  }

  async getOneMessage(message){
    return message;
  }

}
