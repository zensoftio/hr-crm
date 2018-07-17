import json

from django.conf import settings
from rest_framework import status
from rest_framework.response import Response
from rest_framework.views import APIView

from apps.utils.rabbitmq import RabbitMQ


class TemplateListCreateView(APIView):

    def get(self, request, *args, **kwargs):

        data = {'title': 'FIND_ALL'}
        data = json.dumps(data)

        rabbitmq_sender = RabbitMQ(host=settings.RABBITMQ_HOST, user=settings.RABBITMQ_USERNAME,
                                   password=settings.RABBITMQ_PASSWORD)
        rabbitmq_sender.call(exchange_name='js-backend', exchange_type='direct',
                             queue_to_send='template', routing_key_to_send='template',
                             queue_to_receive='template-response',
                             message=data)

        response_data = rabbitmq_sender.response.decode('utf-8')
        response_data = json.loads(response_data)
        return Response(response_data, status=status.HTTP_200_OK)

    def post(self, request, *args, **kwags):
        data = request.data
        data['title'] = 'CREATE'

        rabbitmq_sender = RabbitMQ(host=settings.RABBITMQ_HOST, user=settings.RABBITMQ_USERNAME,
                                   password=settings.RABBITMQ_PASSWORD)
        rabbitmq_sender.call(exchange_name='js-backend', exchange_type='direct',
                             queue_to_send='template', routing_key_to_send='template',
                             queue_to_receive='template-response',
                             message=data)
        response_data = rabbitmq_sender.response.decode('utf-8')
        response_data = json.loads(response_data)
        response_data.pop('title')
        return Response(response_data, status=status.HTTP_201_CREATED)


class TemplateDetailView(APIView):

    def get(self, request, pk, *args, **kwargs):
        data = {
            "title": "FIND_ONE",
            "id": pk
        }
        data = json.dumps(data)

        rabbitmq_sender = RabbitMQ(host=settings.RABBITMQ_HOST, user=settings.RABBITMQ_USERNAME,
                                   password=settings.RABBITMQ_PASSWORD)
        rabbitmq_sender.call(exchange_name='js-backend', exchange_type='direct',
                             queue_to_send='template', routing_key_to_send='template',
                             queue_to_receive='template-response',
                             message=data)
        response_data = rabbitmq_sender.response.decode('utf-8')
        response_data = json.loads(response_data)
        response_status = status.HTTP_200_OK
        if response_data.get('status'):
            response_status = status.HTTP_404_NOT_FOUND

        return Response(response_data, status=response_status)

    def put(self, request, pk, *args, **kwargs):
        data = request.data
        data['title'] = 'UPDATE'
        data['id'] = pk
        data = json.dumps(data)

        rabbitmq_sender = RabbitMQ(host=settings.RABBITMQ_HOST, user=settings.RABBITMQ_USERNAME,
                                   password=settings.RABBITMQ_PASSWORD)
        rabbitmq_sender.call(exchange_name='js-backend', exchange_type='direct',
                             queue_to_send='template', routing_key_to_send='template',
                             queue_to_receive='template-response',
                             message=data)

        response_data = rabbitmq_sender.response.decode('utf-8')
        response_data = json.loads(response_data)
        response_data.pop('title')
        return Response(response_data, status=status.HTTP_201_CREATED)

    def delete(self, request, pk, *args, **kwargs):
        data = {
            "title": "DELETE",
            "id": pk
        }
        data = json.dumps(data)

        rabbitmq_sender = RabbitMQ(host=settings.RABBITMQ_HOST, user=settings.RABBITMQ_USERNAME,
                                   password=settings.RABBITMQ_PASSWORD)
        rabbitmq_sender.call(exchange_name='js-backend', exchange_type='direct',
                             queue_to_send='template', routing_key_to_send='template',
                             queue_to_receive='template-response',
                             message=data)

        response_data = rabbitmq_sender.response.decode('utf-8')
        response_data = json.loads(response_data)
        return Response(response_data)
