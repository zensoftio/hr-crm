from rest_framework import serializers

from apps.departments.models import Requirement, Department
from apps.departments.serializers import RequirementSerializer
from apps.requests.models import Request
from apps.users.serializers import AuxUserSerializer
from .models import Vacancy, Publication


class VacancyDepartmentSerializer(serializers.ModelSerializer):
    class Meta:
        model = Department
        exclude = ['id']


class VacancyRequirementSerializer(serializers.ModelSerializer):
    department = VacancyDepartmentSerializer()

    class Meta:
        model = Requirement
        fields = ('department', 'name', 'type')


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


class PublicationListSerializer(serializers.ModelSerializer):
    created_by = AuxUserSerializer()
    vacancy = VacancyListSerializer()

    class Meta:
        model = Publication
        exclude = []


class PublicationCreateSerializer(serializers.ModelSerializer):
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


class VacancyCreateOrUpdateSerializer(serializers.ModelSerializer):
    class Meta:
        model = Request
        exclude = []
