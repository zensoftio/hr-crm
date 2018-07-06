import uuid

import pika
from rest_framework.renderers import JSONRenderer


class RabbitMQ:
    """ Class for instantiating sending event """

    def __init__(self, host='localhost', user='', password='', routing_key=''):
        self.parameters = ''
        if host == 'localhost':
            self.parameters = pika.ConnectionParameters(host='localhost')
        elif user and password:
            self.parameters = pika.URLParameters('amqp://' + user + ':' + password + '@' + host + ':5672')
        self.routing_key = routing_key
        self.connection = pika.BlockingConnection(self.parameters)
        self.channel = self.connection.channel()

    @staticmethod
    def construct_message(query_set, serializer):
        queryset = query_set

        serializer = serializer(queryset, many=True)

        message = JSONRenderer().render(serializer.data)

        return message

    def call(self, queryset, serializer, exchange_name='', exchange_type='topic', queue=''):
        self.channel.queue_declare(queue=queue, durable=True)
        self.channel.exchange_declare(exchange=exchange_name, exchange_type=exchange_type, durable=True)
        self.corr_id = str(uuid.uuid4())
        self.channel.basic_publish(exchange=exchange_name,
                                   routing_key=queue,
                                   properties=pika.BasicProperties(
                                       delivery_mode=2,  # make message persistent
                                       correlation_id=self.corr_id,
                                   ),
                                   body=self.construct_message(queryset, serializer))
        self.connection.close()
