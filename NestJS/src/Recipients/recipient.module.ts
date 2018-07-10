import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';

import { Recipient } from './recipient.entity';

@Module({
  imports: [TypeOrmModule.forFeature([Recipient])]
})
export class RecipientModule {}
