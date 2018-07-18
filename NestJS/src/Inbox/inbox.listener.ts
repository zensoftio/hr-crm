import { Controller } from '@nestjs/common';
import * as Amqp from "amqp-ts";
import { CronJob } from 'cron';
import { InboxService } from './inbox.service';
import * as moment from 'moment';
import * as connection from 'Rabbit';

const exchange = connection.default.declareExchange('js-backend', 'direct', { durable: false });
const queue = connection.default.declareQueue('candidate',{durable: false});

@Controller('inbox')
export class InboxListener {
    constructor(private readonly inboxService: InboxService){
      this.startCron('00 00 08 * * 1-5',true,'Asia/Bishkek');
      this.startCron('00 00 13 * * 1-5',true,'Asia/Bishkek');
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

    updateInboxList = async(message: any): Promise<any> => {
      try {
        const result = await this.inboxService.getMessages(message);
        this.sendMessage(result);
      }catch(err) {
        this.sendMessage(err);
        throw err;
      }
   }

     getOneMessage = async(message: any): Promise<any> =>{
       try {
         const result = await this.inboxService.getOneMessage(message);
         this.sendMessage(result);
       }catch(err) {
         this.sendMessage(err);
         throw err;
       }
    }

    private async sendMessage(msg: any): Promise<any> {
      var sendQueue = connection.default.declareQueue('candidate-response',{durable:false});
      connection.default.completeConfiguration().then(() => {
          var msg2 = new Amqp.Message(msg);
          exchange.send(msg2,'candidate-response');
      });
    }

    private async listenQueue():Promise<void> {
      queue.bind(exchange, 'candidate');
      queue.activateConsumer((message) => {
        var msg = message.getContent()
        msg = JSON.parse(msg)
        this.distributionTasks(msg);
        }, {noAck: true})
    }

    private async startCron(date: string, a: any, b: any): Promise<any> {
      var job = new CronJob(date,(): Promise<any> => {
        const date = moment().tz('Asia/Bishkek').format("YYYY-MM-DDTHH:mm:ss");
        var data = {
          "date": date
        }
         return this.updateInboxList(data);
        },
        a,
        b
      );
      return true;
    }

}
