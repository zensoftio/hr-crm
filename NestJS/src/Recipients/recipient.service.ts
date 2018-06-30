import { Injectable, Inject } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository, getRepository } from 'typeorm';
import { Recipient } from './recepient.entity';
import { Message } from 'Messages/message.entity';

@Injectable()
export class RecipientService {
    constructor(
        @InjectRepository(Recipient)
        private readonly recipientrepository: Repository<Recipient>,
        ) {}

  async create(recipient: Recipient): Promise<Recipient> {
    await this.recipientrepository.save(recipient)
    return recipient;
  }

  async findAll(): Promise<Recipient[]> {
    return await this.recipientrepository.find();
  }

  async findOne(int: number): Promise<Recipient> {
    return await this.recipientrepository.findOne({id: int});
  }

  async deleteOne(int: number): Promise<Recipient> {
    try {
        const toDelete = this.recipientrepository.findOne({id: int});
        await this.recipientrepository.delete({id: int});
        return toDelete;
    } catch (e) {
        console.log(e);
    }
  }
}
