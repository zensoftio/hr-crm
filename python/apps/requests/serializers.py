from rest_framework import serializers

from apps.departments.serializers import AuxPositionSerializer
from apps.requests.models import Request


class RequestSerializer(serializers.ModelSerializer):
    position = AuxPositionSerializer()

    class Meta:
        model = Request
        fields = ('id', 'position', 'status', 'count', 'created', 'created_by', 'modified',)


class RequestPostSerializer(serializers.ModelSerializer):

    class Meta:
        model = Request
        fields = ('count', 'position', 'created_by')
