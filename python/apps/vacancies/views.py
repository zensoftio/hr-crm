from rest_framework import generics
from rest_framework.response import Response
from rest_framework.status import HTTP_201_CREATED

from apps.utils.serializers import MethodSerializerView
from .models import Vacancy, Publication
from apps.requests.models import Request
from .serializers import VacancyListSerializer, VacancyCreateUpdateSerializer, VacancyDetailSerializer, \
    PublicationListSerializer, PublicationCreateSerializer


class VacancyListView(generics.ListCreateAPIView):
    queryset = Vacancy.objects.all()
    serializer_class = VacancyListSerializer

    def create(self, request, *args, **kwargs):
        write_serializer = VacancyCreateUpdateSerializer(data=request.data)
        write_serializer.is_valid(raise_exception=True)
        self.perform_create(write_serializer)
        read_serializer = VacancyDetailSerializer(write_serializer.instance)
        return Response(read_serializer.data, status=HTTP_201_CREATED)


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


