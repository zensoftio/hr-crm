import json

import pika
from rest_framework.renderers import JSONRenderer


class RabbitMQ:
    """ Class for instantiating sending and receiving events """

    def __init__(self, host='localhost', user='', password=''):
        self.parameters = ''
        if host == 'localhost':
            self.parameters = pika.ConnectionParameters(host='localhost')
        elif user and password:
            self.parameters = pika.URLParameters('amqp://' + user + ':' + password + '@' + host + ':5672')
        self.routing_key = ''
        self.connection = pika.BlockingConnection(self.parameters)
        self.channel = self.connection.channel()
        self.queue = ''

    @staticmethod
    def message_to_server(query_set, serializer):
        queryset = query_set

        serializer = serializer(queryset)

        message = JSONRenderer().render(serializer.data)

        return message

    @staticmethod
    def message_to_client(body):
        return body['status']

    @staticmethod
    def callback(ch, method, properties, body):
        print(" [x] %r:%r" % (method.routing_key, body))
        ch.basic_ack(delivery_tag=method.delivery_tag)

    def call(self, queryset, serializer, exchange_name='', exchange_type='topic', queue='', routing_key=''):
        self.queue = queue
        self.routing_key = routing_key
        self.channel.queue_declare(queue=self.queue, durable=True)
        self.channel.exchange_declare(exchange=exchange_name, exchange_type=exchange_type, durable=True)
        message = self.message_to_server(queryset, serializer)
        cor_id = json.loads(message)
        self.channel.basic_publish(exchange=exchange_name,
                                   routing_key=self.routing_key,
                                   properties=pika.BasicProperties(
                                       delivery_mode=2,  # make message persistent
                                       correlation_id=cor_id['uuid'],
                                   ),
                                   body=message)
        self.connection.close()

    def consume(self, exchange_name='', exchange_type='topic', routing_key='', queue=''):
        self.channel.queue_declare(queue=queue, durable=True)
        self.channel.exchange_declare(exchange=exchange_name, exchange_type=exchange_type, durable=True)
        self.routing_key = routing_key
        self.channel.queue_bind(exchange=exchange_name,
                                queue=queue,
                                routing_key=routing_key)
        print(' [*] Waiting for Messages. To exit press CTRL+C')

        self.channel.basic_consume(self.callback,
                                   queue=queue,
                                   )
        self.channel.start_consuming()
