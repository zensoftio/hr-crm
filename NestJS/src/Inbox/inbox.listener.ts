import { Controller } from '@nestjs/common';
import * as Amqp from "amqp-ts";
import { CronJob } from 'cron';
import { InboxService } from './inbox.service';
import { Inbox } from './inbox.interface';
import * as moment from 'moment';
import * as connection from 'Rabbit';

const exchange = connection.default.declareExchange('js-backend', 'direct', { durable: false });
const queue = connection.default.declareQueue('candidate_queue',{durable: false});

@Controller('inbox')
export class InboxListener {
    constructor(private readonly inboxService: InboxService){
      this.startCron('00 00 08 * * 1-5');
      this.startCron('00 00 13 * * 1-5');
      this.listenQueue();
    }

    public distributionTasks(task: any):void{
      const obj = {
        UPDATE: this.updateInboxList,
        GET_ONE: this.getOneMessage,
      };
      if (obj[task.title]) {
        obj[task.title](task);
      } else {
        this.sendMessage({"status": 400});
      }
    }

    updateInboxList = async(message: any):any => {
      try {
        const result = await this.inboxService.getMessages(message)
        this.sendMessage(result)
      }catch(err) {
        this.sendMessage(err)
        throw err;
      }
   }

     getOneMessage = async(message: any):any => {
       try {
         const result = await this.inboxService.getOneMessage(message)
         this.sendMessage(result)
       }catch(err) {
         this.sendMessage(err)
         throw err;
       }
    }

    private async sendMessage(msg: any):any {
      connection.completeConfiguration().then(() => {
          var msg2 = new Amqp.Message(msg);
          exchange.send(msg2,queue);
      });
    }

    private async listenQueue():void {
      queue.bind(exchange, 'candidate-listen');
      queue.activateConsumer((message) => {
        var msg = message.getContent()
        msg = JSON.parse(msg)
        this.distributionTasks(msg);
        }, {noAck: true})
    }

    private async startCron(date: string):void{
      var job = new CronJob(date, () => {
        const date = moment().tz('Asia/Bishkek').format("YYYY-MM-DDTHH:mm:ss");
        var data = {
          "date": date
        }
         this.updateInboxList(data);
        },
        true,
        'Asia/Bishkek'
      );
    }

}
