import { Injectable } from '@nestjs/common';
import * as gmail from '../gmail_api/gmailapi'
import { InjectRepository } from '@nestjs/typeorm';
import { Repository, createQueryBuilder } from 'typeorm';
import { Inboxes } from './inbox.entity';

@Injectable()
export class InboxService {
  constructor(
    @InjectRepository(Inboxes)
    private readonly inboxRepository: Repository<Inboxes>
    ){}

  async updateDate(date: any): Promise<any> {
    try {
      const toUpdate = await this.inboxRepository.findOne();
      await this.inboxRepository.update(toUpdate.id, {last_update: date} );
      return toUpdate;
    }catch (e){
      throw e;
    }
  }

  async getMessages(message: any): Promise<any>{
    const date = await this.updateDate(message.date);
    return await gmail.getAllMessages(message.date);
  }

  async getOneMessage(message: any): Promise<any>{
    return message;
  }

}
