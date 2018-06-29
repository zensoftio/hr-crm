import * as amqp from 'amqplib';
import { Server, CustomTransportStrategy } from '@nestjs/microservices';
import { Observable } from 'rxjs';
import * as Event from './Event/event.controller';
export class RabbitMQServer extends Server implements CustomTransportStrategy {
    private server: amqp.Connection = null;
    private channel: amqp.Channel = null;
    constructor(
      private readonly host: string,
      private readonly queue: string) {
        super();
      }

  public async listen(callback: () => void) {
    await this.init();
    let jsonObj = {
      "title": "Event",
      "body": {
        "description": "create",
        "content": {
          "id": "1",
          "date": "28-26-2018",
          "begin_time": "29-06-2018T9:50",
          "end_time": "29-06-2018T16:00",
          "email": ["shisyr2106@gmail.com", "shisyr96@gmail.com"],
          "description": "Dessdflslkfcription",
          "location": "Location",
          "summary": "Interview"
        }
      }
    }
    await this.sendMessage(JSON.stringify(jsonObj));
    this.channel.consume(`${this.queue}_sub`, this.handleMessage.bind(this), {
      noAck: true,
    });

  }

  public close() {
    this.channel && this.channel.close();
    this.server && this.server.close();
  }

  private async handleMessage(message) {
    const { content } = message;
    const messageObj = JSON.parse(content.toString());
    this.sendToListener(JSON.parse(messageObj));
    const handlers = this.getHandlers();
    const pattern = JSON.stringify(messageObj.pattern);
    if (!this.messageHandlers[pattern]) {
        return;
    }
    const handler = this.messageHandlers[pattern];
    const response$ = this.transformToObservable(await handler(messageObj.data)) as Observable<any>;
    response$ && this.send(response$, (data) => this.sendMessage(data));
  }
  private sendToListener(msg){
    var event = new Event.EventController();
    event.sendMessage(msg.body)
    event.doEvent();
  }

  private sendMessage(message) {
    const buffer = Buffer.from(JSON.stringify(message));
    this.channel.sendToQueue(`${this.queue}_sub`, buffer);
  }

  private async init() {
    this.server = await amqp.connect(this.host);
    this.channel = await this.server.createChannel();
    this.channel.assertQueue(`${this.queue}_sub`, { durable: false });
    this.channel.assertQueue(`${this.queue}_pub`, { durable: false });
  }
}
