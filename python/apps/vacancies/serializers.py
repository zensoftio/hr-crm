from rest_framework import serializers

from apps.departments.serializers import RequirementSerializer, DepartmentSerializer
from apps.requests.models import Request
from apps.users.serializers import AuxUserSerializer
from .models import Vacancy, Publication
from apps.departments.models import Requirement


class VacancyRequirementSerializer(serializers.ModelSerializer):
    department = DepartmentSerializer()

    class Meta:
        model = Requirement
        fields = ('id', 'department', 'name', 'type')


class VacancyListSerializer(serializers.ModelSerializer):
    name = serializers.CharField(source='request.position.name')

    class Meta:
        model = Vacancy
        fields = ('id', 'name', 'created', 'last_published')


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
    requirements = RequirementSerializer(many=True, source='request.requirements', read_only=True)
    request = VacancyRequestSerializer(read_only=True)
    name = serializers.CharField(source='request.position.name', read_only=True)

    class Meta:
        model = Vacancy
        exclude = ['uuid']


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
    requirements = VacancyRequirementSerializer(many=True, source='request.requirements')

    class Meta:
        model = Vacancy
        fields = ('uuid', 'title', 'requirements', 'city',
                  'address', 'name', 'count', 'work_conditions', 'working_hours',
                  'salary_min', 'salary_max', 'image', 'responsibilities'
                  )


class VacancyPartialUpdateSerializer(serializers.ModelSerializer):

    class Meta:
        model = Vacancy
        fields = ('working_hours')
