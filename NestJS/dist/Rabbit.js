"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
const Amqp = require("amqp-ts");
const connection = new Amqp.Connection("amqp://guest:guest@159.65.153.5:5672");
exports.default = connection;
//# sourceMappingURL=Rabbit.js.map