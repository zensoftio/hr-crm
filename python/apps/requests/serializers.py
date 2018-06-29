from rest_framework import serializers

from apps.departments.serializers import AuxPositionSerializer, RequirementSerializer
from apps.requests.models import Request
from apps.departments.models import Requirement


class RequestSerializer(serializers.ModelSerializer):
    position = AuxPositionSerializer()
    # requirements = serializers.PrimaryKeyRelatedField(many=True, queryset=Requirement.objects.all())
    # requirements = RequirementSerializer(many=True, source='position.department.requirements')

    class Meta:
        model = Request
        fields = ('id', 'position', 'status', 'count', 'created', 'created_by', 'modified', 'requirements')


class RequestPostSerializer(serializers.ModelSerializer):
    requirements = serializers.PrimaryKeyRelatedField(many=True, queryset=Requirement.objects.all())

    class Meta:
        model = Request
        # fields = ('position', 'count', 'created_by', 'requirements')
        fields = ('id', 'position', 'status', 'count', 'created', 'created_by', 'modified', 'requirements')

    # def to_representation(self, instance):
    #     data = super(RequestPostSerializer, self).to_representation(instance)
    #     # result_data = {'status': 201, 'Allow': ['GET', 'POST', 'HEAD', 'OPTION'], }
    #     result_data = {'response': ''}
    #     result_data['response'] = data
    #     return result_data


    # def to_representation(self, instance):
    #     representation = super(RequestPostSerializer, self).to_representation(instance)
    #     representation['position'] = RequestPostSerializer(instance.assigment_set.all(), many=True).data
    #     return representation
