from django.contrib import messages
from rest_framework import generics
from rest_framework.response import Response
from rest_framework.status import HTTP_201_CREATED, HTTP_200_OK

from apps.utils.serializers import MethodSerializerView
from .models import Vacancy, Publication
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
    serializer_class = VacancyDetailSerializer

    def partial_update(self, request, *args, **kwargs):
        partial = kwargs.pop('partial', True)
        instance = self.get_object()
        write_serializer = VacancyCreateUpdateSerializer(
            instance, data=request.data, partial=partial
        )
        write_serializer.is_valid(raise_exception=True)
        self.perform_update(write_serializer)

        if getattr(instance, '_prefetched_objects_cache', None):
            instance._prefetched_objects_cache = {}

        read_serializer = VacancyDetailSerializer(instance)

        return Response(read_serializer.data)


class PublicationList(MethodSerializerView, generics.ListCreateAPIView):
    queryset = Publication.objects.all()

    method_serializer_classes = {
        ('GET',): PublicationListSerializer,
        ('POST',): PublicationCreateSerializer
    }

    def create(self, request, *args, **kwargs):
        data = request.data
        queryset = Vacancy.objects.get(pk=data['vacancy'])
        if data['facebook']:
            send_message_to_java.delay(queryset, JavaVacancySerializer, 'facebook')
        if data['jobkg']:
            send_message_to_java.delay(queryset, JavaVacancySerializer, 'jobKg')
        if data['diesel']:
            send_message_to_java.delay(queryset, JavaVacancySerializer, 'diesel')

        messages.success(self.request, "We are publicating. wait a moment and refresh")
        print("Message from create method")
        return Response(data, status=HTTP_200_OK)


class PublicationDetail(generics.RetrieveAPIView):
    queryset = Publication.objects.all()
    serializer_class = PublicationListSerializer
