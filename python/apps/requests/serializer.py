from django.contrib.auth import get_user_model
from rest_framework import serializers

from apps.requests.models import Request
from apps.departments.serializers import AuxPositionSerializer

User = get_user_model()


class RequestListSerializer(serializers.ModelSerializer):
    position = AuxPositionSerializer()

    class Meta:
        model = Request
        fields = ('id', 'position', 'status', 'count', 'created')


class RequestInterviewSerializer(serializers.ModelSerializer):
    class Meta:
        model = Request
        fields = ('id', 'status', 'created')
