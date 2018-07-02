from django.shortcuts import render
from rest_framework import generics, status
from rest_framework.response import Response

from apps.templates.models import Template
from apps.templates.serializers import TemplateListSerialzer


class TemplateListCreateView(generics.ListCreateAPIView):
    queryset = Template.objects.all()
    serializer_class = TemplateListSerialzer
