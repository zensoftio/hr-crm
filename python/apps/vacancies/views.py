from rest_framework import generics
from rest_framework.response import Response

from .models import Vacancy, Publication
from .serializers import VacancySerializer, PublicationSerializer, AuxVacancySerializer


class VacancyListView(generics.ListCreateAPIView):
    queryset = Vacancy.objects.all()
    serializer_class = AuxVacancySerializer

    def create(self, request, *args, **kwargs):
        write_serializer = VacancySerializer(data=request.data)
        write_serializer.is_valid(raise_exception=True)
        self.perform_create(write_serializer)
        read_serializer = AuxVacancySerializer(write_serializer.instance)
        return Response(read_serializer.data)


class VacancyDetailView(generics.RetrieveUpdateDestroyAPIView):
    queryset = Vacancy.objects.all()
    serializer_class = VacancySerializer


class PublicationList(generics.ListAPIView):
    queryset = Vacancy.objects.all()
    serializer_class = VacancySerializer


class PublicationDetail(generics.RetrieveAPIView):
    queryset = Publication.objects.all()
    serializer_class = PublicationSerializer
