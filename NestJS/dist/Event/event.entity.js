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
let Event = class Event {
};
__decorate([
    typeorm_1.PrimaryGeneratedColumn(),
    __metadata("design:type", Number)
], Event.prototype, "id", void 0);
__decorate([
    typeorm_1.Column(),
    __metadata("design:type", String)
], Event.prototype, "id_event", void 0);
__decorate([
    typeorm_1.Column(),
    __metadata("design:type", String)
], Event.prototype, "begin_time", void 0);
__decorate([
    typeorm_1.Column(),
    __metadata("design:type", String)
], Event.prototype, "end_time", void 0);
__decorate([
    typeorm_1.Column('text'),
    __metadata("design:type", Array)
], Event.prototype, "email", void 0);
__decorate([
    typeorm_1.Column('text'),
    __metadata("design:type", Array)
], Event.prototype, "phone", void 0);
__decorate([
    typeorm_1.Column(),
    __metadata("design:type", String)
], Event.prototype, "description", void 0);
__decorate([
    typeorm_1.Column(),
    __metadata("design:type", String)
], Event.prototype, "location", void 0);
Event = __decorate([
    typeorm_1.Entity()
], Event);
exports.Event = Event;
//# sourceMappingURL=event.entity.js.map