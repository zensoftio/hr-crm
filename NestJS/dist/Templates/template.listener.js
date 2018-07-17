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
const template_service_1 = require("./template.service");
const connection = require("Rabbit");
const exchange = connection.default.declareExchange("js-backend", 'direct', { durable: false });
const queue = connection.default.declareQueue('template', { durable: false });
let TemplateListener = class TemplateListener {
    constructor(templateService) {
        this.templateService = templateService;
        this.findAllTemplate = (template) => __awaiter(this, void 0, void 0, function* () {
            try {
                const result = yield this.templateService.findAll();
                this.sendMessage(result);
            }
            catch (err) {
                this.sendMessage(err);
                throw err;
            }
        });
        this.findOneTemplate = (template) => __awaiter(this, void 0, void 0, function* () {
            try {
                const result = yield this.templateService.findOne(template.id);
                this.sendMessage(result);
            }
            catch (err) {
                this.sendMessage("CAN'T_FIND_ONE");
                throw err;
            }
        });
        this.deleteTemplate = (template) => __awaiter(this, void 0, void 0, function* () {
            try {
                const result = yield this.templateService.deleteOne(template.id);
                this.sendMessage(result);
            }
            catch (err) {
                this.sendMessage("CAN'T_DELETE");
                throw err;
            }
        });
        this.updateTemplate = (template) => __awaiter(this, void 0, void 0, function* () {
            try {
                const result = yield this.templateService.update(template.id, template);
                this.sendMessage(result);
            }
            catch (err) {
                this.sendMessage("CAN'T_UPDATE");
                throw err;
            }
        });
        this.createTemplate = (template) => __awaiter(this, void 0, void 0, function* () {
            try {
                const result = yield this.templateService.create(template);
                this.sendMessage(result);
            }
            catch (err) {
                this.sendMessage(err);
                throw err;
            }
        });
        this.listenQueue();
    }
    distributionTasks(task) {
        console.log(task.title);
        const obj = {
            CREATE: this.createTemplate,
            UPDATE: this.updateTemplate,
            DELETE: this.deleteTemplate,
            FIND_ALL: this.findAllTemplate,
            FIND_ONE: this.findOneTemplate,
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
            var sendQueue = connection.default.declareQueue('template-response', { durable: false });
            connection.default.completeConfiguration().then(() => {
                var msg2 = new Amqp.Message(msg);
                exchange.send(msg2, 'template-response', { durable: false });
                console.log(msg2 + "MSG2");
            });
        });
    }
    listenQueue() {
        return __awaiter(this, void 0, void 0, function* () {
            queue.bind(exchange, 'template', { durable: false });
            queue.activateConsumer((message) => {
                var msg = message.getContent();
                msg = JSON.parse(msg);
                this.distributionTasks(msg);
            }, { noAck: true });
        });
    }
};
TemplateListener = __decorate([
    common_1.Controller('template'),
    __metadata("design:paramtypes", [template_service_1.TemplateService])
], TemplateListener);
exports.TemplateListener = TemplateListener;
//# sourceMappingURL=template.listener.js.map