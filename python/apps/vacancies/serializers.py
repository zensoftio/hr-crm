from rest_framework import serializers

from apps.requests.serializer import RequestSerializer
from .models import Vacancy, Publication


class VacancySerializer(serializers.ModelSerializer):
    created_by = serializers.ReadOnlyField(source='created_by.email')
    request = RequestSerializer

    class Meta:
        model = Vacancy
        exclude = []


class PublicationSerializer(serializers.ModelSerializer):
    vacancy = VacancySerializer

    class Meta:
        model = Publication
        exclude =[]

