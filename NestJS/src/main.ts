import { NestFactory } from '@nestjs/core';
import { AppModule } from './app.module';
import { MessageController } from 'Messages/message.listener';
import { MessageModule } from 'Messages/message.module';
import { Service, Container } from 'typedi';
import { RabbitConnection } from './Rabbit';
import * as Amqp from "amqp-ts";
async function bootstrap() {
  global.connection = new Amqp.Connection("amqp://guest:guest@localhost:5672");
  const app = await NestFactory.create(AppModule);
  await app.listen(3000);
}
bootstrap();
