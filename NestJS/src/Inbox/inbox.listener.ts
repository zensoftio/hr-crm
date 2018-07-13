import { Controller } from '@nestjs/common';
import * as Amqp from "amqp-ts";
import { CronJob } from 'cron';
import { InboxService } from './inbox.service';
import { Inbox } from './inbox.interface';
import * as moment from 'moment';
import * as connection from 'Rabbit';

const exchange = connection.default.declareExchange('candidates', 'direct', { durable: false });
const queue = connection.default.declareQueue('candidate_queue',{durable: false});

@Controller('inbox')
export class InboxListener {
    constructor(private readonly inboxService: InboxService){
      this.startCron('00 00 08 * * 1-5');
      this.startCron('00 00 13 * * 1-5');
      this.listenQueue();
    }

    async testService(){
      var data = {
        date: "2018-07-13T10:15:00",
      }
      const a = await this.updateInboxList(data);
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
        this.sendMessage("CAN'T GET ONE MESSAGE")
        throw err;
      }
   }

     getOneMessage = async(message: any):any => {
       try {
         const result = await this.inboxService.getOneMessage(message)
         this.sendMessage(result)
       }catch(err) {
         this.sendMessage("CAN'T GET ONE MESSAGE")
         throw err;
       }
    }

    private async sendMessage(msg: any):any {
      var sendQueue = connection.declareQueue("inboxMessages2")
      connection.completeConfiguration().then(() => {
          var msg2 = new Amqp.Message(msg);
          exchange.send(msg2);
      });
    }

    private async listenQueue():void {
      queue.bind(exchange, 'inboxMessages');
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
