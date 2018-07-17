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
const event_service_1 = require("./event.service");
const common_1 = require("@nestjs/common");
const Amqp = require("amqp-ts");
const connection = require("Rabbit");
const exchange = connection.default.declareExchange("js-backend", 'direct', { durable: false });
const queue = connection.default.declareQueue('event', { durable: false });
let EventController = class EventController {
    constructor(eventService) {
        this.eventService = eventService;
        this.initRabbitMQ();
    }
    getDataFromService(message) {
        return __awaiter(this, void 0, void 0, function* () {
            let msg = {
                'title': message.title,
                'body': message.body
            };
            try {
                if (msg.title === 'create') {
                    msg.body = yield this.eventService.createEvent(msg);
                }
                else if (msg.title === 'update') {
                    msg.body = yield this.eventService.updateEvent(msg);
                }
                else if (msg.title === 'getone') {
                    msg.body = yield this.eventService.getOne(msg);
                }
                else if (msg.title === 'getlist') {
                    msg.body = yield this.eventService.getList();
                }
                else if (msg.title === 'remove') {
                    msg.body = yield this.eventService.removeEvent(msg);
                }
                else {
                    msg.body = yield 'Incorrect title';
                }
            }
            catch (err) {
                msg.body = err;
            }
            this.sendResponse(msg);
        });
    }
    sendResponse(res) {
        return __awaiter(this, void 0, void 0, function* () {
            connection.default.declareQueue("event-response", { durable: false });
            connection.default.completeConfiguration().then(() => {
                const msg2 = new Amqp.Message(JSON.stringify(res));
                exchange.send(msg2, 'event-response');
                console.log(' [x] Sent event-response  \'' + msg2.getContent() + '\'');
            });
        });
    }
    initRabbitMQ() {
        queue.bind(exchange, 'event');
        queue.activateConsumer((message) => {
            const data = JSON.parse(message.getContent());
            console.log("GET");
            console.log(data);
            console.log("GET");
            this.getDataFromService(data);
        }, { noAck: true });
    }
};
EventController = __decorate([
    common_1.Controller('event'),
    __metadata("design:paramtypes", [event_service_1.EventService])
], EventController);
exports.EventController = EventController;
//# sourceMappingURL=event.listener.js.map