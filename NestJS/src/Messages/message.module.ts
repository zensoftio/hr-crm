import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { MessageService } from './message.service'

import { Message } from './message.entity';
import { MessageListener } from './message.listener';
import { RecipientService } from 'Recipients/recipient.service';
import { Recipient } from 'Recipients/recipient.entity';

@Module({
  imports: [TypeOrmModule.forFeature([Message]), TypeOrmModule.forFeature([Recipient])],
  providers: [MessageService, RecipientService],
  controllers: [MessageListener],
})
export class MessageModule {}
