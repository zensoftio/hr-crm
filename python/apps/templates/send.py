import pika
import json


class Send():

    def _connect(self):
        """Connect to the RabbitMQ server."""

        connection = pika.BlockingConnection(pika.URLParameters('amqp://mqadmin:mqadminpassword@192.168.89.113:5672'))
        channel = connection.channel()

        return connection, channel

    def publish_request(self, message):
        """put the message of the RabbitMQ queue."""

        connection, channel = self._connect()
        channel.exchange_declare(exchange='exchangeForTemplate',
                                 exchange_type='direct')

        message = json.dumps(message)
        # message = json.loads(message)
        # print(str(message) + " here")
        channel.basic_publish(exchange='exchangeForTemplate',
                              routing_key='template',
                              body=message)
        print(" [x] Sent %r" % message)
        connection.close()
