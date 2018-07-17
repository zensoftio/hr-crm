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
var __param = (this && this.__param) || function (paramIndex, decorator) {
    return function (target, key) { decorator(target, key, paramIndex); }
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
const typeorm_1 = require("@nestjs/typeorm");
const typeorm_2 = require("typeorm");
const event_entity_1 = require("./event.entity");
const google = require("./calendar/google.calendar");
const sms = require("./phone_notification/sms");
let EventService = class EventService {
    constructor(eventRepository) {
        this.eventRepository = eventRepository;
    }
    createEvent(event) {
        return __awaiter(this, void 0, void 0, function* () {
            return new Promise(function (resolve, reject) {
                google.run(event, function (err, response) {
                    let eventOfDatabase = event.body;
                    if (!err) {
                        eventOfDatabase.id_event = response.id;
                        typeorm_2.getRepository(event_entity_1.Event).insert(eventOfDatabase);
                        (sms.send(eventOfDatabase, 'create')) ? true : reject('Cannot send for all phone_numbers');
                    }
                    err ? reject(err) : resolve(eventOfDatabase);
                });
            });
        });
    }
    getList() {
        return __awaiter(this, void 0, void 0, function* () {
            let eventOfDatabase = yield typeorm_2.getRepository(event_entity_1.Event).find({});
            if (!eventOfDatabase)
                eventOfDatabase = "There is no events!";
            return eventOfDatabase;
        });
    }
    getOne(event) {
        return __awaiter(this, void 0, void 0, function* () {
            let eventOfDatabase = yield typeorm_2.getRepository(event_entity_1.Event).findOne({ id_event: event.body.id_event });
            if (!eventOfDatabase)
                eventOfDatabase = "whether invalid id_event or no such event";
            return eventOfDatabase;
        });
    }
    updateEvent(event) {
        return __awaiter(this, void 0, void 0, function* () {
            return new Promise((resolve, reject) => __awaiter(this, void 0, void 0, function* () {
                const eventOfDatabase = yield typeorm_2.getRepository(event_entity_1.Event).findOne({ id_event: event.body.id_event });
                if (!eventOfDatabase) {
                    reject("whether invalid id_event or no such event!");
                }
                else {
                    event.body.id = eventOfDatabase.id;
                    event.body.id_event = eventOfDatabase.id_event;
                    google.run(event, function (err, response) {
                        if (!err) {
                            typeorm_2.getRepository(event_entity_1.Event).save(event.body);
                            (sms.send(event.body, 'update')) ? true : reject('Cannot send for all phone_numbers');
                        }
                        err ? reject(err) : resolve(event.body);
                    });
                }
            }));
        });
    }
    removeEvent(event) {
        return __awaiter(this, void 0, void 0, function* () {
            return new Promise((resolve, reject) => __awaiter(this, void 0, void 0, function* () {
                const eventOfDatabase = yield typeorm_2.getRepository(event_entity_1.Event).findOne({ id_event: event.body.id_event });
                if (!eventOfDatabase) {
                    resolve("whether invalid id_event or no such event!");
                }
                else {
                    const eventForSms = event.body;
                    event.body = eventOfDatabase;
                    google.run(event, function (err, response) {
                        typeorm_2.getRepository(event_entity_1.Event).delete(eventOfDatabase);
                        (sms.send(eventForSms, 'delete')) ? true : reject('Cannot send for all phone_numbers');
                        err ? reject(err) : resolve(response);
                    });
                }
            }));
        });
    }
};
EventService = __decorate([
    common_1.Injectable(),
    __param(0, typeorm_1.InjectRepository(event_entity_1.Event)),
    __metadata("design:paramtypes", [typeorm_2.Repository])
], EventService);
exports.EventService = EventService;
//# sourceMappingURL=event.service.js.map