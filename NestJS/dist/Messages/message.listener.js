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
const qs = require("../gmail_api/gmailapi");
const message_service_1 = require("./message.service");
const common_1 = require("@nestjs/common");
const Amqp = require("amqp-ts");
const recipient_service_1 = require("Recipients/recipient.service");
const connection = require("Rabbit");
const exchange = connection.default.declareExchange("js-backend", 'direct', { durable: false });
const queue = connection.default.declareQueue('message', { durable: false });
let MessageListener = class MessageListener {
    constructor(messageService, recipientService) {
        this.messageService = messageService;
        this.recipientService = recipientService;
        this.listenQueue();
    }
    listenQueue() {
        queue.bind(exchange, 'message');
        queue.activateConsumer((message) => {
            var msg = message.getContent();
            var data = JSON.parse(msg);
            this.takeAction(data);
        }, { noAck: true });
    }
    takeAction(msg) {
        let res;
        switch (msg.task) {
            case "send":
                res = this.sendEmail(msg);
                break;
            case "get":
                res = this.getMessages(msg);
                break;
        }
        return res;
    }
    getMessages(data) {
        return __awaiter(this, void 0, void 0, function* () {
            const msgs = yield this.messageService.findByRecipient(data.recipient);
            console.log(msgs);
            const response = JSON.stringify(msgs);
            console.log("------");
            console.log(response);
        });
    }
    sendResponse(res) {
        connection.default.declareQueue("message-response");
        connection.default.completeConfiguration().then(() => {
            var msg2 = new Amqp.Message(res);
            exchange.send(msg2);
            console.log(' [x] Sent message-response  \'' + msg2.getContent() + '\'');
        });
    }
    sendEmail(data) {
        return __awaiter(this, void 0, void 0, function* () {
            const results = yield Promise.all(data.recipients.map((element) => qs.sendMessageH(data, element)));
            try {
                yield this.saveRecipientsToDb(data);
            }
            catch (err) {
                console.log(err);
            }
            this.sendResponse(results);
        });
    }
    saveRecipientsToDb(data) {
        return __awaiter(this, void 0, void 0, function* () {
            const msgId = yield this.messageService.create(data);
            data.recipients.map((rec) => rec.message = msgId);
            console.log(data);
            return this.recipientService.create(data.recipients);
        });
    }
};
MessageListener = __decorate([
    common_1.Controller('messages'),
    __metadata("design:paramtypes", [message_service_1.MessageService,
        recipient_service_1.RecipientService])
], MessageListener);
exports.MessageListener = MessageListener;
//# sourceMappingURL=message.listener.js.map