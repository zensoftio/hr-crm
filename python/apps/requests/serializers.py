from rest_framework import serializers

from apps.departments.serializers import AuxPositionSerializer, RequirementSerializer
from apps.requests.models import Request


class RequestSerializer(serializers.ModelSerializer):
    position = AuxPositionSerializer()
    requirements = RequirementSerializer(many=True, source='position.department.requirements')

    class Meta:
        model = Request
        fields = ('id', 'position', 'status', 'count', 'created', 'created_by', 'modified', 'requirements')


class RequestPostSerializer(serializers.ModelSerializer):
    # requirements = RequirementSerializer(many=True, source='position.department.requirements')

    class Meta:
        model = Request
        fields = ('position', 'count', 'status', 'created_by')

    # def to_representation(self, instance):
    #     representation = super(RequestPostSerializer, self).to_representation(instance)
    #     representation['position'] = RequestPostSerializer(instance.assigment_set.all(), many=True).data
    #     return representation
