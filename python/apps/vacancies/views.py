from rest_framework import generics
from rest_framework.response import Response
from rest_framework.status import HTTP_201_CREATED

from apps.utils.serializers import MethodSerializerView

from apps.utils.serializers import MethodSerializerView
from .models import Vacancy, Publication
from .serializers import VacancyListSerializer, VacancyCreateUpdateSerializer, VacancyDetailSerializer, \
    PublicationListSerializer, PublicationCreateSerializer


class VacancyListView(MethodSerializerView, generics.ListCreateAPIView):
    queryset = Vacancy.objects.all()

    method_serializer_classes = {
        ('GET',): VacancyListSerializer,
        ('POST',): VacancyCreateUpdateSerializer
    }


class VacancyDetailView(generics.RetrieveUpdateAPIView):
    queryset = Vacancy.objects.all()
    serializer_class = VacancyDetailSerializer


class PublicationList(MethodSerializerView, generics.ListCreateAPIView):
    queryset = Publication.objects.all()

    method_serializer_classes = {
        ('GET',): PublicationListSerializer,
        ('POST',): PublicationCreateSerializer
    }


class PublicationDetail(generics.RetrieveAPIView):
    queryset = Publication.objects.all()
    serializer_class = PublicationListSerializer


