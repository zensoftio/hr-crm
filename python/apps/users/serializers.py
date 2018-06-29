from django.contrib.auth import get_user_model
from rest_framework import serializers

from apps.departments.serializers import DepartmentSerializer

User = get_user_model()


class UserSerializer(serializers.ModelSerializer):
    departments = DepartmentSerializer(many=True, read_only=True)

    class Meta:
        model = User
        fields = ('id', 'email', 'first_name', 'last_name', 'departments', 'created')


class UserInterviewSerializer(serializers.ModelSerializer):
    class Meta:
        model = User
        fields = ('id', 'email', 'first_name', 'last_name')
