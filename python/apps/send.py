#!/usr/bin/env python
import pika
from rest_framework.renderers import JSONRenderer


class Send:
    """ Class for instantiating sending event """
    def __init__(self, serializer, queryset, host='localhost', user='guest',
                 password='guest', exchange_name='', exchange_type='fanout'):
        self.serializer = serializer
        self.host = host
        self.user = user
        self.password = password
        self.exchange_name = exchange_name
        self.exchange_type = exchange_type
        self.queryset = queryset

    def _connect(self):

        if self.user and self.password:
            parameters = pika.URLParameters('amqp://' + self.user + ':' + self.password + '@' + self.host + ':5672')
        else:
            parameters = pika.ConnectionParameters('localhost')
        connection = pika.BlockingConnection(parameters)
        channel = connection.channel()

        return connection, channel

    def construct_message(self):
        queryset = self.queryset

        serializer = self.serializer(queryset, many=True)

        message = JSONRenderer().render(serializer.data)

        return message

    def publish(self):
        connection, channel = self._connect()

        channel.exchange_declare(exchange=self.exchange_name,
                                 exchange_type=self.exchange_type)
        channel.basic_publish(exchange=self.exchange_name,
                              routing_key='',
                              body=self.construct_message())
