import json

from django.conf import settings
from rest_framework import status
from rest_framework.response import Response
from rest_framework.views import APIView

from apps.utils.rabbitmq import RabbitMQ


class TemplateListCreateView(APIView):

    def get(self, request, *args, **kwargs):
        data = [
            {
                'id': 1,
                'body': 'Body sefjskldf',
                'subject': '23423',
                'attachment': [
                    '12312312',
                    '123123dsfsdf',
                    '31312312'
                ]
            },
            {
                'id': 2,
                'body': 'Body sefjskldf',
                'subject': '23423',
                'attachment': [
                    '12312312',
                    '123123dsfsdf',
                    '31312312'
                ]
            },
            {
                'id': 3,
                'body': 'Body sefjskldf',
                'subject': '23423',
                'attachment': [
                    '12312312',
                    '123123dsfsdf',
                    '31312312'
                ]
            }

        ]
        # data = {'title': 'FIND_ALL'}
        # data = json.dumps(data)
        # print(type(data), data)
        #
        # rabbitmq_sender = RabbitMQ(user=settings.RABBITMQ_USERNAME,
        #                            password=settings.RABBITMQ_PASSWORD)
        #
        # rabbitmq_sender.call(exchange_name='exchangeForTemplate',
        #                      exchange_type='direct',
        #                      queue_to_send='template',
        #                      routing_key_to_send='template',
        #                      queue_to_receive='template3',
        #                      message=data)
        #
        # response_data = rabbitmq_sender.response.decode('utf-8')
        # response_data = json.loads(response_data)
        return Response(data)

    def post(self, request, *args, **kwags):
        data = request.data
        data['title'] = 'CREATE'
        data = json.dumps(data)
        print(type(data), data)

        rabbitmq_sender = RabbitMQ(user=settings.RABBITMQ_USERNAME, password=settings.RABBITMQ_PASSWORD)
        rabbitmq_sender.call(exchange_name='exchangeForTemplate', exchange_type='direct',
                             queue_to_send='template', routing_key_to_send='template',
                             queue_to_receive='template3',
                             message=data)
        response_data = rabbitmq_sender.response.decode('utf-8')
        response_data = json.loads(response_data)
        return Response(response_data)


class TemplateDetailView(APIView):

    def get(self, request, pk, *args, **kwargs):
        pk = str(pk)
        data = {
            'id': pk,
            'body': 'Body' + pk,
            'subject': pk,
            'attachment': [
                '12312312',
                '123123dsfsdf',
                '31312312'
            ]
        }
        # data = {
        #     "title": "FIND_ONE",
        #     "id": pk
        # }
        # data = json.dumps(data)
        # print(type(data), data)
        #
        # rabbitmq_sender = RabbitMQ(user=settings.RABBITMQ_USERNAME, password=settings.RABBITMQ_PASSWORD)
        # rabbitmq_sender.call(exchange_name='exchangeForTemplate', exchange_type='direct',
        #                      queue_to_send='template', routing_key_to_send='template',
        #                      queue_to_receive='template3',
        #                      message=data)
        # response_data = rabbitmq_sender.response.decode('utf-8')
        # response_data = json.loads(response_data)
        return Response(data)

    def put(self, request, pk, *args, **kwargs):
        data = request.data
        data['title'] = 'UPDATE'
        data['id'] = pk
        data = json.dumps(data)
        print(type(data), data)

        # rabbitmq_sender = RabbitMQ(user=settings.RABBITMQ_USERNAME,
        #                            password=settings.RABBITMQ_PASSWORD)
        #
        # rabbitmq_sender.call(exchange_name='exchangeForTemplate',
        #                      exchange_type='direct',
        #                      queue_to_send='template',
        #                      routing_key_to_send='template',
        #                      queue_to_receive='template3',
        #                      message=data)
        #
        # response_data = rabbitmq_sender.response.decode('utf-8')
        # response_data = json.loads(response_data)
        return Response(data)

    def delete(self, request, pk, *args, **kwargs):
        data = {
            "title": "DELETE",
            "id": pk
        }
        data = json.dumps(data)
        print(type(data), data)

        # rabbitmq_sender = RabbitMQ(user=settings.RABBITMQ_USERNAME,
        #                            password=settings.RABBITMQ_PASSWORD)
        #
        # rabbitmq_sender.call(exchange_name='exchangeForTemplate',
        #                      exchange_type='direct',
        #                      queue_to_send='template',
        #                      routing_key_to_send='template',
        #                      queue_to_receive='template3',
        #                      message=data)
        #
        # response_data = rabbitmq_sender.response.decode('utf-8')
        # response_data = json.loads(response_data)
        return Response(data=data, status=status.HTTP_204_NO_CONTENT)
