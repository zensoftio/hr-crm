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
    uuid = serializers.UUIDField(format='hex')
    created_by = AuxUserSerializer(read_only=True)
    requirements = RequirementSerializer(many=True, source='request.requirements')
    request = VacancyRequestSerializer()
    name = serializers.CharField(source='request.position.name')

    class Meta:
        model = Vacancy
        exclude = []


class VacancyPublicationSerializer(serializers.ModelSerializer):
    class Meta:
        model = Vacancy
        fields = ['id', 'created_by']


class PublicationSerializer(serializers.ModelSerializer):
    created_by = AuxUserSerializer

    class Meta:
        model = Publication
        exclude = []


class JavaVacancySerializer(serializers.ModelSerializer):
    name = serializers.CharField(source='request.position.name')
    count = serializers.IntegerField(source='request.count')
    uuid = serializers.UUIDField(format='hex')
    requirements = RequirementSerializer(many=True, source='request.requirements')

    class Meta:
        model = Vacancy
        fields = ('uuid', 'title', 'requirements', 'city',
                  'address', 'name', 'count', 'work_conditions',
                  'employment_type', 'salary_min', 'salary_max', 'image', 'responsibilities'
                  )
