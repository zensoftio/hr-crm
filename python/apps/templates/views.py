import json

from rest_framework import generics
from rest_framework.response import Response
from rest_framework.views import APIView

from apps.templates.models import Attachment
from apps.templates.serializers import AttachmentSerializer
from apps.utils.rabbitmq import RabbitMQ
from django.conf import settings


class TemplateListCreateView(APIView):

    def get(self, request, *args, **kwargs):
        data = {'title': 'FIND_ALL'}
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
        data = {
            "title": "FIND_ONE",
            "id": pk
        }
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

    def put(self, request, pk, *args, **kwargs):
        data = request.data
        data['title'] = 'UPDATE'
        data['id'] = pk
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

    def delete(self, request, pk, *args, **kwargs):
        data = {
            "title": "DELETE",
            "id": pk
        }
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


class AttachmentListCreateView(generics.ListCreateAPIView):
    queryset = Attachment.objects.all()
    serializer_class = AttachmentSerializer
