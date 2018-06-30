from rest_framework import serializers

from apps.departments.models import Requirement
from .models import Vacancy, Publication


class VacancySerializer(serializers.ModelSerializer):
    requirements = serializers.PrimaryKeyRelatedField(many=True, queryset=Requirement.objects.all())

    class Meta:
        model = Vacancy
        fields = '__all__'


class AuxVacancySerializer(serializers.ModelSerializer):
    name = serializers.CharField(source='request.position.name')

    class Meta:
        model = Vacancy
        fields = ('id', 'name', 'created', 'last_published', 'status')


class PublicationSerializer(serializers.ModelSerializer):
    class Meta:
        model = Publication
        fields = ('vacancy_id', 'created', 'created_by', 'facebook',
                  'instagram', 'headhunter', 'diesel', 'jobkg')
