from rest_framework import serializers
from apps.requests.models import Request
from django.contrib.auth import get_user_model


User = get_user_model()


class RequestSerializer(serializers.ModelSerializer):

    class Meta:
        model = Request
        fields = ('id', 'position', 'status', 'count', 'created')
