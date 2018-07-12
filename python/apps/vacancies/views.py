from rest_framework import generics
from rest_framework.response import Response
from rest_framework.status import HTTP_201_CREATED, HTTP_200_OK

from .models import Vacancy, Publication
from .serializers import VacancyListSerializer, VacancyCreateUpdateSerializer, VacancyDetailSerializer, \
    PublicationSerializer, VacancyPartialUpdateSerializer, JavaVacancySerializer

from .tasks import send_message_to_java
from django.contrib import messages
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


class PublicationList(generics.ListCreateAPIView):
    queryset = Publication.objects.all()
    serializer_class = PublicationSerializer

    def create(self, request, *args, **kwargs):
        data = request.data
        queryset = Vacancy.objects.get(pk=1)
        send_message_to_java.delay(queryset, JavaVacancySerializer)
        messages.success(self.request, "We are publicating. wait a moment and refresh")
        
        return Response(data, status="HTTP_201_CREATED")

class PublicationDetail(generics.RetrieveAPIView):
    queryset = Publication.objects.all()
    serializer_class = PublicationSerializer

