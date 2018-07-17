"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
Object.defineProperty(exports, "__esModule", { value: true });
const common_1 = require("@nestjs/common");
const typeorm_1 = require("@nestjs/typeorm");
const inbox_service_1 = require("./inbox.service");
const inbox_listener_1 = require("./inbox.listener");
const inbox_entity_1 = require("./inbox.entity");
let InboxModule = class InboxModule {
};
InboxModule = __decorate([
    common_1.Module({
        imports: [typeorm_1.TypeOrmModule.forFeature([inbox_entity_1.Inboxes])],
        providers: [inbox_service_1.InboxService],
        controllers: [inbox_listener_1.InboxListener]
    })
], InboxModule);
exports.InboxModule = InboxModule;
//# sourceMappingURL=inbox.module.js.map