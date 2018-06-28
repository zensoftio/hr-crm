from rest_framework import generics

from .models import Vacancy, Publication
from .serializers import VacancySerializer, PublicationSerializer


class VacancyList(generics.ListCreateAPIView):
    queryset = Vacancy.objects.all()
    serializer_class = VacancySerializer

    def perform_create(self, serializer):
        serializer.save(created_by=self.request.user)


class VacancyDetail(generics.RetrieveUpdateDestroyAPIView):
    queryset = Vacancy.objects.all()
    serializer_class = VacancySerializer


class PublicationList(generics.ListAPIView):
    queryset = Publication.objects.all()
    serializer_class = PublicationSerializer


class PublicationDetail(generics.RetrieveAPIView):
    queryset = Publication.objects.all()
    serializer_class = PublicationSerializer



