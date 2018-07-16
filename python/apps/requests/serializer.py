from django.contrib.auth import get_user_model
from rest_framework import serializers

from apps.departments.serializers import AuxPositionSerializer, RequirementSerializer
from apps.requests.models import Request
from apps.users.serializers import AuxUserSerializer

User = get_user_model()


class RequestListSerializer(serializers.ModelSerializer):
    position = AuxPositionSerializer()

    class Meta:
        model = Request
        fields = ('id', 'position', 'status', 'count', 'created', 'is_vacancy_created')


class RequestCreateOrUpdateSerializer(serializers.ModelSerializer):
    class Meta:
        model = Request
        exclude = []


class RequestDetailSerializer(serializers.ModelSerializer):
    created_by = AuxUserSerializer(read_only=True)
    position = AuxPositionSerializer(read_only=True)
    requirements = RequirementSerializer(many=True, read_only=True)

    class Meta:
        model = Request
        fields = ('id', 'created_by', 'position', 'requirements', 'created', 'status', 'count', 'modified')


class RequestInterviewSerializer(serializers.ModelSerializer):
    class Meta:
        model = Request
        fields = ('id', 'status', 'created')
