import { Module } from '@nestjs/common';

import { TypeOrmModule } from '@nestjs/typeorm';
import { Connection } from 'typeorm';

import { AppController } from './app.controller';
import { AppService } from './app.service';

import { TemplateModule } from './Templates/template.module'
import { MessageModule } from './Messages/message.module'
import { RecepientModule } from './Recipients/recepient.module'
import { EventModule } from './Event/event.module';

@Module({
  imports: [
    TemplateModule,
    MessageModule,
    RecepientModule,
   EventModule,
    TypeOrmModule.forRoot()
  ],
  controllers: [AppController],
  providers: [AppService],
})

export class AppModule {
  constructor(private readonly connection: Connection) {}
}
