import * as amqp from 'amqplib';
import { Server, CustomTransportStrategy } from '@nestjs/microservices';
import { Observable } from 'rxjs';
import * as Event from './Event/event.listener';
export class RabbitMQServer extends Server implements CustomTransportStrategy {
    private server: amqp.Connection = null;
    private channel: amqp.Channel = null;

    constructor(
      private readonly host: string,
      private readonly queue: string) {
        super();
      }

  public async listen(callback: () => void) {
    console.log("Microservice is Liestening")
    await this.init();
    var jsonObj = {
      "title": "Event",
      "body": {
        "description": "create",
        "content":{
          "id": "1",
          "id_event": "284mddk392dsk392skd",
          "date": "29-06-2018",
          "begin_time": "29-06-2018T12:00",
          "end_time": "29-06-2018T18:00",
          "email": ["shisyr2106@gmail.com", "shisyr96@gmail.com"],
          "description": "Description",
          "location": "Sovetskaya",
          "summary": "Interview"
        }
      }
    }
    console.log(jsonObj);
    this.sendMessage(JSON.stringify(jsonObj))
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
  private sendToListener(message){
    var event = new Event.EventListener();
    console.log(message);
    event.sendMessage(message.body);
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
