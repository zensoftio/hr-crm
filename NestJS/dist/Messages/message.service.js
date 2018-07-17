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
const message_entity_1 = require("./message.entity");
let MessageService = class MessageService {
    constructor(messageRepository) {
        this.messageRepository = messageRepository;
    }
    create(message) {
        return __awaiter(this, void 0, void 0, function* () {
            let messageToDatabase = {
                "subject": message.subject,
                "content": message.content,
                "template": message.template
            };
            let response;
            try {
                response = yield this.messageRepository.save(messageToDatabase);
            }
            catch (err) {
                messageToDatabase.template = null;
                response = yield this.messageRepository.save(messageToDatabase);
            }
            return yield JSON.stringify(response.id);
        });
    }
    findAll() {
        return __awaiter(this, void 0, void 0, function* () {
            return yield this.messageRepository.find();
        });
    }
    findByRecipient(recipientEmail) {
        return __awaiter(this, void 0, void 0, function* () {
            const myMessages = yield this.messageRepository
                .createQueryBuilder("message")
                .leftJoinAndSelect("message.recipients", "recipient")
                .where("recipient.email = :email", { email: recipientEmail })
                .getMany();
            return myMessages;
        });
    }
    findOne(int) {
        return __awaiter(this, void 0, void 0, function* () {
            return yield this.messageRepository.findOne({ id: int });
        });
    }
    deleteOne(int) {
        return __awaiter(this, void 0, void 0, function* () {
            try {
                const toDelete = this.messageRepository.findOne({ id: int });
                yield this.messageRepository.delete({ id: int });
                return toDelete;
            }
            catch (e) {
                console.log(e);
            }
        });
    }
};
MessageService = __decorate([
    common_1.Injectable(),
    __param(0, typeorm_1.InjectRepository(message_entity_1.Message)),
    __metadata("design:paramtypes", [typeorm_2.Repository])
], MessageService);
exports.MessageService = MessageService;
//# sourceMappingURL=message.service.js.map