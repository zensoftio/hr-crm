import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { InboxService } from './inbox.service';
import { InboxListener } from './inbox.listener';
import { Inboxes } from './inbox.entity';

@Module({
  imports: [TypeOrmModule.forFeature([Inboxes])],
  providers: [InboxService],
  controllers: [InboxListener]
})
export class InboxModule {}
