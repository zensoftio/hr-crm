from rest_framework import generics

from apps.templates.models import Template
from apps.templates.serializers import TemplateListSerializer


class TemplateListCreateView(generics.ListCreateAPIView):
    queryset = Template.objects.all()
    serializer_class = TemplateListSerializer
