
import * as Amqp from "amqp-ts";

const connection = new Amqp.Connection("amqp://guest:guest@rabbitmq:5672");

export default connection;
