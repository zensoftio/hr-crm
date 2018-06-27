from django.contrib.auth import get_user_model
from rest_framework import serializers

from apps.departments.serializers import DepartmentSerializer

User = get_user_model()


class UserSerializer(serializers.ModelSerializer):
    department = DepartmentSerializer()

    class Meta:
        model = User
        fields = ('id', 'email', 'first_name', 'last_name', 'department', 'created')
