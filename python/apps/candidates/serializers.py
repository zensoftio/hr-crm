from rest_framework import serializers

from apps.candidates.models import Candidate
from apps.departments.serializers import DepartmentSerializer


class CandidateDetailSerializer(serializers.ModelSerializer):
    class Meta:
        model = Candidate
        fields = ('id','first_name', 'last_name', 'email', 'phone', 'experience',
                  'level', 'status', 'vacancy', 'skype', 'position',)
        depth = 3


class CandidateInterviewSerializer(serializers.ModelSerializer):
    department = DepartmentSerializer(source='position.department')

    class Meta:
        model = Candidate
        fields = ('id', 'first_name', 'last_name', 'department')


class CandidateListSerializer(serializers.ModelSerializer):
    class Meta:
        model = Candidate
        depth = 2
        fields = ('first_name', 'last_name', 'position')
