import { Injectable, Inject } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository, getRepository } from 'typeorm';
import { Message } from './message.entity';
import { acceleratedmobilepageurl } from 'googleapis/build/src/apis/acceleratedmobilepageurl';
import { Recipient } from 'Recipients/recepient.entity';
import { RecipientService } from 'Recipients/recipient.service';

@Injectable()
export class MessageService {
    constructor(
        @InjectRepository(Message)
        private readonly messageRepository: Repository<Message>, 
        private readonly recipientService: RecipientService
        ) {}

  async create(message: Message): Promise<number> {

    const response = await this.messageRepository.save(message)
    const id = +await JSON.stringify(response.id)
    return id
  }

  async findAll(): Promise<Message[]> {
    return await this.messageRepository.find();
  }

  async findOne(int: number): Promise<Message> {
    return await this.messageRepository.findOne({id: int});
  }

  async deleteOne(int: number): Promise<Message> {
    try {
        const toDelete = this.messageRepository.findOne({id: int});
        await this.messageRepository.delete({id: int});
        return toDelete;
    } catch (e) {
        console.log(e);
    }
  }
}
