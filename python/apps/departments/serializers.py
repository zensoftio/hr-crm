from rest_framework import serializers
from apps.departments.models import Department


class DepartamentSerializer(serializers.ModelSerializer):
    class Meta:
        model = Department
        fields = ('id', 'name')
