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
        switch(data.task){
            case "create":
            templateService.create(data)
            .then((done) => console.log("done"))
            .catch((err) => console.log("error occured " +err))
            break;
            case "update":
            templateService.update(data)
            console.log("Successfully updated the template")
            case "delete":
            templateService.deleteOne(data.id)
            console.log("Successfully deleted the template")
        }
        
        }, {noAck: true})

    }
}
