import { Injectable, Inject } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { Message } from './message.entity';

@Injectable()
export class MessageService {
    constructor(
        @InjectRepository(Message)
        private readonly messageRepository: Repository<Message>,
        ) {}

  create(message: Message): Message {
    this.messageRepository.save(message);
    return message;
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
