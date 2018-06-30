from rest_framework import serializers

from apps.departments.models import Requirement
from apps.departments.serializers import AuxPositionSerializer, RequirementSerializer
from apps.requests.models import Request
from apps.users.serializers import UserSerializer


class RequestSerializer(serializers.ModelSerializer):
    position = AuxPositionSerializer()

    class Meta:
        model = Request
        fields = ('id', 'position', 'status', 'count', 'created')


class RequestPostSerializer(serializers.ModelSerializer):
    class Meta:
        model = Request
        fields = ('id', 'position', 'status', 'count', 'created', 'created_by', 'modified', 'requirements')


class RequestCreatedSerializer(serializers.ModelSerializer):
    position = AuxPositionSerializer()
    requirements = RequirementSerializer(
        many=True,
        instance=serializers.PrimaryKeyRelatedField(
            many=True,
            queryset=Requirement.objects.all()))
    created_by = UserSerializer()

    class Meta:
        model = Request
        fields = ('id', 'position', 'status', 'count', 'created', 'created_by', 'modified', 'requirements')
