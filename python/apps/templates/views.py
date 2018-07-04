from rest_framework import generics, status
from rest_framework.response import Response

from apps.templates.models import Template, Attachment
from apps.templates.serializers import TemplateListSerializer, TemplateCreateSerializer, \
    TemplateDetailSerializer, AttachmentSerializer


class TemplateListCreateView(generics.ListCreateAPIView):
    queryset = Template.objects.all()
    serializer_class = TemplateListSerializer

    def create(self, request, *args, **kwags):
        write_serializer = TemplateCreateSerializer(data=request.data)
        write_serializer.is_valid(raise_exception=True)
        self.perform_create(write_serializer)
        read_serializer = TemplateDetailSerializer(write_serializer.instance)
        return Response(read_serializer.data, status=status.HTTP_201_CREATED)


class AttachmentListCreateView(generics.ListCreateAPIView):
    queryset = Attachment.objects.all()
    serializer_class = AttachmentSerializer
