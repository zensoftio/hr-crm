import { NestFactory } from '@nestjs/core';
import { AppModule } from './app.module';
import * as Amqp from "amqp-ts";

async function bootstrap() {
  global.connection = new Amqp.Connection("amqp://localhost");
  const app = await NestFactory.create(AppModule);
  await app.listen(3000);
}
bootstrap();
