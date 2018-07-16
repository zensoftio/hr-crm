import json

import pika
from rest_framework.renderers import JSONRenderer


class CountCallback(object):
    def __init__(self, count):
        self.count = count
        self.body = None

    def __call__(self, ch, method, properties, body):
        self.body = body
        self.count -= 1
        print("callback ", self.count, self.body)
        if not self.count:
            ch.stop_consuming()

        return self.body


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
        self.response = None

    @staticmethod
    def message_to_server(query_set, serializer):
        queryset = query_set

        serializer = serializer(queryset)

        message = JSONRenderer().render(serializer.data)

        return message

    @staticmethod
    def message_to_client(body):
        return body['status']

    def call(self, queryset=None, serializer=None, exchange_name='', exchange_type='',
             queue_to_send='', routing_key_to_send='',
             queue_to_receive='', routing_key_to_receive='',
             message=''):

        self.queue_to_send = queue_to_send
        self.routing_key_to_send = routing_key_to_send

        if queryset and serializer:
            message = self.message_to_server(queryset, serializer)
            cor_id = json.loads(message)
            uuid = cor_id['uuid']
        else:
            message = message
            uuid = ''

        self.channel.exchange_declare(exchange=exchange_name, exchange_type=exchange_type, durable=False)
        self.channel.queue_declare(queue=self.queue, durable=False)

        self.channel.basic_publish(exchange=exchange_name,
                                   routing_key=routing_key_to_send,
                                   body=message)
        print(" [x] Sent %r" % message)

        self.channel.queue_bind(exchange=exchange_name,
                                queue=queue_to_receive)
        self.channel.queue_declare(queue=queue_to_receive, durable=False)
        callback = CountCallback(1)
        self.channel.basic_consume(callback, no_ack=True,
                                   queue=queue_to_receive)

        self.channel.start_consuming()
        self.response = callback.body
        print(" [.] Got %r" % self.response)

        return self.response
