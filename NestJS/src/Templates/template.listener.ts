import { Controller } from '@nestjs/common';
import * as Amqp from "amqp-ts";
import { TemplateService } from './template.service';

var connection = new Amqp.Connection("amqp://localhost");
var exchange = connection.declareExchange("test3", 'direct', { durable: false });
var queue = connection.declareQueue('');

@Controller('template')
export class TemplateListener {
    constructor(private readonly templateService: TemplateService){
      queue.bind(exchange, 'template');
      queue.activateConsumer((message) => {
        var msg = message.getContent()
        var data = JSON.parse(msg)
        console.log(msg)
        templateService.create(data)
        }, {noAck: true})

    }
}
