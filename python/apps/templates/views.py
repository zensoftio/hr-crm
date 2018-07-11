from rest_framework import generics, status
from rest_framework.response import Response
from rest_framework.views import APIView

from apps.templates.models import Template, Attachment
from apps.templates.serializers import TemplateListSerializer, TemplateDetailSerializer, AttachmentSerializer
from apps.utils.rabbitmq import RabbitMQ
from config.base import Base
import json


class TemplateListCreateView(APIView):
    # queryset = Template.objects.all()
    # serializer_class = TemplateListSerializer
    # filter_fields = ('type',)

    def get(self, request, *args, **kwargs):
        data = {'title': 'FIND_ALL'}
        data = json.dumps(data)
        print(type(data), data)

        rabbitmq_sender = RabbitMQ(user=Base.RABBITMQ_USERNAME, password=Base.RABBITMQ_PASSWORD)
        rabbitmq_sender.call(exchange_name='exchangeForTemplate', exchange_type='direct',
                             queue_to_send='template', routing_key_to_send='template',
                             queue_to_receive='template3',
                             message=data)
        r = rabbitmq_sender.response.decode('utf-8')
        r = json.loads(r)
        return Response(r)

    def create(self, request, *args, **kwags):
        data = request.data
        data['title'] = 'CREATE'
        data = json.dumps(data)
        print(type(data), data)

        rabbitmq_sender = RabbitMQ(user=Base.RABBITMQ_USERNAME, password=Base.RABBITMQ_PASSWORD)
        rabbitmq_sender.call(exchange_name='exchangeForTemplate', exchange_type='direct',
                             queue_to_send='template', routing_key_to_send='template',
                             queue_to_receive='template3',
                             message=data)
        r = rabbitmq_sender.response.decode('utf-8')
        r = json.loads(r)
        return Response(r)

        # write_serializer = TemplateCreateSerializer(data=request.data)
        # write_serializer.is_valid(raise_exception=True)
        # self.perform_create(write_serializer)
        # read_serializer = TemplateDetailSerializer(write_serializer.instance)
        # return Response(read_serializer.data, status=status.HTTP_201_CREATED)


class TemplateDetailView(APIView):
    # queryset = Template.objects.all()
    # serializer_class = TemplateDetailSerializer

    def get(self, request, pk, format=None, *args, **kwargs):
        data = {
            "title": "FIND_ONE",
            "id": pk
        }
        # data = {
        #     "title": "UPDATE",
        #     "id": 3,
        #     "subject": "Erik",
        #     "body": "Erik",
        #     "attachment": "[a,b,c,d]"
        # }
        data = json.dumps(data)
        print(type(data), data)

        rabbitmq_sender = RabbitMQ(user=Base.RABBITMQ_USERNAME, password=Base.RABBITMQ_PASSWORD)
        rabbitmq_sender.call(exchange_name='exchangeForTemplate', exchange_type='direct',
                             queue_to_send='template', routing_key_to_send='template',
                             queue_to_receive='template3',
                             message=data)
        r = rabbitmq_sender.response.decode('utf-8')
        r = json.loads(r)
        return Response(r)

    def update(self, request, pk, format=None, *args, **kwargs):
        data = request.data
        data['title'] = 'UPDATE'
        data['id'] = pk
        data = json.dumps(data)
        print(type(data), data)

        data = {
            "task": "UPDATE",
            "id": 2,
            "subject": "Erik",
            "body": "Erik",
            "attachment": "Erik"
        }
        data = json.dumps(data)

        rabbitmq_sender = RabbitMQ(user=Base.RABBITMQ_USERNAME, password=Base.RABBITMQ_PASSWORD)
        rabbitmq_sender.call(exchange_name='exchangeForTemplate', exchange_type='direct',
                             queue_to_send='template', routing_key_to_send='template',
                             queue_to_receive='template3',
                             message=data)
        r = rabbitmq_sender.response.decode('utf-8')
        r = json.loads(r)
        return Response(r)

    def delete(self, request, pk, format=None, *args, **kwargs):
        data = {
            "title": "DELETE",
            "id": pk
        }
        data = json.dumps(data)
        print(type(data), data)
        rabbitmq_sender = RabbitMQ(user=Base.RABBITMQ_USERNAME, password=Base.RABBITMQ_PASSWORD)
        rabbitmq_sender.call(exchange_name='exchangeForTemplate', exchange_type='direct',
                             queue_to_send='template', routing_key_to_send='template',
                             queue_to_receive='template3',
                             message=data)
        r = rabbitmq_sender.response.decode('utf-8')
        r = json.loads(r)
        return Response(r)


class AttachmentListCreateView(generics.ListCreateAPIView):
    queryset = Attachment.objects.all()
    serializer_class = AttachmentSerializer
