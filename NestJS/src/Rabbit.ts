
import * as Amqp from "amqp-ts";

const connection = new Amqp.Connection("amqp://guest:guest@localhost:5672");

export default connection;
