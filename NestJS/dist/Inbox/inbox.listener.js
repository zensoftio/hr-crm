"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : new P(function (resolve) { resolve(result.value); }).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
Object.defineProperty(exports, "__esModule", { value: true });
const common_1 = require("@nestjs/common");
const Amqp = require("amqp-ts");
const cron_1 = require("cron");
const inbox_service_1 = require("./inbox.service");
const moment = require("moment");
const connection = require("Rabbit");
const exchange = connection.default.declareExchange('js-backend', 'direct', { durable: false });
const queue = connection.default.declareQueue('candidate', { durable: false });
let InboxListener = class InboxListener {
    constructor(inboxService) {
        this.inboxService = inboxService;
        this.updateInboxList = (message) => __awaiter(this, void 0, void 0, function* () {
            try {
                const result = yield this.inboxService.getMessages(message);
                this.sendMessage(result);
            }
            catch (err) {
                this.sendMessage(err);
                throw err;
            }
        });
        this.getOneMessage = (message) => __awaiter(this, void 0, void 0, function* () {
            try {
                const result = yield this.inboxService.getOneMessage(message);
                this.sendMessage(result);
            }
            catch (err) {
                this.sendMessage(err);
                throw err;
            }
        });
        this.startCron('00 00 08 * * 1-5');
        this.startCron('00 00 13 * * 1-5');
        this.listenQueue();
    }
    distributionTasks(task) {
        const obj = {
            UPDATE: this.updateInboxList,
            GET_ONE: this.getOneMessage,
        };
        if (obj[task.title]) {
            obj[task.title](task);
        }
        else {
            this.sendMessage({ "status": 400 });
        }
    }
    sendMessage(msg) {
        return __awaiter(this, void 0, void 0, function* () {
            var sendQueue = connection.default.declareQueue('candidate-response', { durable: false });
            connection.default.completeConfiguration().then(() => {
                var msg2 = new Amqp.Message(msg);
                exchange.send(msg2, 'candidate-response', { durable: false });
            });
        });
    }
    listenQueue() {
        return __awaiter(this, void 0, void 0, function* () {
            queue.bind(exchange, 'candidate');
            queue.activateConsumer((message) => {
                var msg = message.getContent();
                msg = JSON.parse(msg);
                this.distributionTasks(msg);
            }, { noAck: true });
        });
    }
    startCron(date) {
        return __awaiter(this, void 0, void 0, function* () {
            var job = new cron_1.CronJob(date, () => {
                const date = moment().tz('Asia/Bishkek').format("YYYY-MM-DDTHH:mm:ss");
                var data = {
                    "date": date
                };
                this.updateInboxList(data);
            }, true, 'Asia/Bishkek');
        });
    }
};
InboxListener = __decorate([
    common_1.Controller('inbox'),
    __metadata("design:paramtypes", [inbox_service_1.InboxService])
], InboxListener);
exports.InboxListener = InboxListener;
//# sourceMappingURL=inbox.listener.js.map