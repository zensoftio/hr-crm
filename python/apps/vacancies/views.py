from django.contrib import messages
from rest_framework import generics
from rest_framework.response import Response
from rest_framework.status import HTTP_201_CREATED, HTTP_200_OK

from apps.utils.serializers import MethodSerializerView
from .models import Vacancy, Publication
from apps.requests.models import Request
from .serializers import VacancyListSerializer, VacancyCreateUpdateSerializer, VacancyDetailSerializer, \
    PublicationListSerializer, JavaVacancySerializer, PublicationCreateSerializer
from .tasks import send_message_to_java


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
    serializer_class = JavaVacancySerializer


class PublicationList(MethodSerializerView, generics.ListCreateAPIView):
    queryset = Publication.objects.all()

    method_serializer_classes = {
        ('GET',): PublicationListSerializer,
        ('POST',): PublicationCreateSerializer
    }

    def create(self, request, *args, **kwargs):
        data = request.data
        queryset = Vacancy.objects.get(pk=data['vacancy'])
        send_message_to_java.delay(queryset, JavaVacancySerializer)
        messages.success(self.request, "We are publicating. wait a moment and refresh")
        print("Message from create method")
        return Response(data, status=HTTP_200_OK)


class PublicationDetail(generics.RetrieveAPIView):
    queryset = Publication.objects.all()
    serializer_class = PublicationListSerializer
