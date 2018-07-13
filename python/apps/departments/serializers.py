from rest_framework import serializers

from apps.departments.models import Department, Requirement, Position


class DepartmentSerializer(serializers.ModelSerializer):
    class Meta:
        model = Department
        fields = ('id', 'name')


class PositionListSerializer(serializers.ModelSerializer):
    department = DepartmentSerializer()

    class Meta:
        model = Position
        fields = ('id', 'department', 'name')


class PositionCreateSerializer(serializers.ModelSerializer):
    class Meta:
        model = Position
        fields = ('id', 'department', 'name')


class RequirementSerializer(serializers.ModelSerializer):
    department = DepartmentSerializer()

    class Meta:
        model = Requirement
        fields = ('id', 'department', 'name', 'type')


class RequirementCreateSerializer(serializers.ModelSerializer):
    class Meta:
        model = Requirement
        fields = ('id', 'department', 'name', 'type')


class AuxPositionSerializer(serializers.ModelSerializer):
    """Position Serializer with nested Department Serializer"""
    department = DepartmentSerializer(read_only=True)

    class Meta:
        model = Position
        fields = ('id', 'department', 'name')
