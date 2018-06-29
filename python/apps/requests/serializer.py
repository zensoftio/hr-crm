from django.contrib.auth import get_user_model
from rest_framework import serializers

from apps.requests.models import Request

User = get_user_model()


class RequestSerializer(serializers.ModelSerializer):
    class Meta:
        model = Request
        fields = ('id', 'position', 'status', 'count', 'created')


class RequestInterviewSerializer(serializers.ModelSerializer):
    class Meta:
        model = Request
        fields = ('id', 'status', 'created')
