import { NestFactory } from '@nestjs/core';
import { AppModule } from './app.module';
import { RabbitMQServer } from './rabbitmq-server';
import { RabbitMQClient } from './rabbitmq-client';
import { MessageController } from 'Messages/message.listener';
import { MessageModule } from 'Messages/message.module';

async function bootstrap() {
  const app = await NestFactory.createMicroservice(AppModule, {
    strategy: new RabbitMQServer('amqp://localhost', 'channel'),
  });
  
  app.listen( () => console.log('Microservice is listening'));
}
bootstrap();
