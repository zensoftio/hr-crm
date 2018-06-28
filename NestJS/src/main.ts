import { NestFactory } from '@nestjs/core';
import { AppModule } from './app.module';
import { NestFactory } from '@nestjs/core';
import { RabbitMQServer } from './rabbitmq-server';
import { RabbitMQClient } from './rabbitmq-client';

async function bootstrap() {
  const app = await NestFactory.createMicroservice(AppModule, {
    strategy: new RabbitMQServer('amqp://localhost', 'channel'),
  });
  app.listen( () => console.log('Microservice is listening'));
}
bootstrap();
