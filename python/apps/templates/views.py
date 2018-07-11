from rest_framework import generics, status
from rest_framework.response import Response

from apps.templates.models import Template, Attachment
from apps.templates.serializers import TemplateListSerializer, TemplateCreateSerializer, \
    TemplateDetailSerializer, AttachmentSerializer
import json
from apps.utils.rabbitmq import RabbitMQ


class TemplateListCreateView(generics.ListCreateAPIView):
    queryset = Template.objects.all()
    serializer_class = TemplateListSerializer
    filter_fields = ('type',)

    def get(self, request, *args, **kwargs):
        data = {'title': 'FIND_ALL'}
        data = json.dumps(data)
        data = json.loads(data)
        print(type(data), data)

        rabbitmq = RabbitMQ()
        return Response(data, status=status.HTTP_200_OK)

    def create(self, request, *args, **kwags):
        data = request.data
        data['title'] = 'CREATE'
        print(type(data), data)

        write_serializer = TemplateCreateSerializer(data=request.data)
        write_serializer.is_valid(raise_exception=True)
        self.perform_create(write_serializer)
        read_serializer = TemplateDetailSerializer(write_serializer.instance)
        return Response(read_serializer.data, status=status.HTTP_201_CREATED)


class TemplateDetailView(generics.RetrieveUpdateAPIView):
    queryset = Template.objects.all()
    serializer_class = TemplateDetailSerializer


class AttachmentListCreateView(generics.ListCreateAPIView):
    queryset = Attachment.objects.all()
    serializer_class = AttachmentSerializer
