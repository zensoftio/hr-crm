from rest_framework import serializers

from apps.departments.serializers import RequirementSerializer
from apps.requests.models import Request
from apps.users.serializers import AuxUserSerializer
from .models import Vacancy, Publication


class VacancyListSerializer(serializers.ModelSerializer):
    name = serializers.CharField(source='request.position.name')

    class Meta:
        model = Vacancy
        fields = ('id', 'name', 'created', 'last_published', 'status')


class VacancyCreateUpdateSerializer(serializers.ModelSerializer):
    class Meta:
        model = Vacancy
        exclude = []


class VacancyRequestSerializer(serializers.ModelSerializer):
    class Meta:
        model = Request
        fields = ('id', 'position')


class VacancyDetailSerializer(serializers.ModelSerializer):
    created_by = AuxUserSerializer(read_only=True)
    requirements = RequirementSerializer(many=True, read_only=True)
    request = VacancyRequestSerializer()

    class Meta:
        model = Vacancy
        exclude = []


class PublicationSerializer(serializers.ModelSerializer):
    class Meta:
        model = Publication
        fields = ('vacancy_id', 'created', 'created_by', 'facebook',
                  'instagram', 'headhunter', 'diesel', 'jobkg')
