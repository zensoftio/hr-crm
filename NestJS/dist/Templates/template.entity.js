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
Object.defineProperty(exports, "__esModule", { value: true });
const typeorm_1 = require("typeorm");
const message_entity_1 = require("../Messages/message.entity");
let Templates = class Templates {
};
__decorate([
    typeorm_1.PrimaryGeneratedColumn(),
    __metadata("design:type", Number)
], Templates.prototype, "id", void 0);
__decorate([
    typeorm_1.Column({ length: 500 }),
    __metadata("design:type", String)
], Templates.prototype, "subject", void 0);
__decorate([
    typeorm_1.Column('text'),
    __metadata("design:type", String)
], Templates.prototype, "body", void 0);
__decorate([
    typeorm_1.Column({ nullable: true }),
    __metadata("design:type", String)
], Templates.prototype, "attachment", void 0);
__decorate([
    typeorm_1.OneToMany(type => message_entity_1.Message, message => message.template),
    __metadata("design:type", Array)
], Templates.prototype, "messages", void 0);
Templates = __decorate([
    typeorm_1.Entity()
], Templates);
exports.Templates = Templates;
//# sourceMappingURL=template.entity.js.map