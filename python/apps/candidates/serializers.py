from rest_framework import serializers


from apps.departments.serializers import DepartmentSerializer
from apps.interviews.serializers import AuxInterviewSerializer
from apps.candidates.models import Candidate
from apps.departments.serializers import PositionSerializer
from .models import Candidate, CV


class CVSerializer(serializers.ModelSerializer):
    class Meta:
        model = CV
        fields = '__all__'


class CandidateDetailSerializer(serializers.ModelSerializer):
    """Candidate serializer for Detailed Candidate endpoint"""
    interviews = AuxInterviewSerializer(many=True)
    CVs = CVSerializer(many=True, source='CV')

    class Meta:
        model = Candidate
        fields = ('id', 'first_name', 'last_name',
                  'email', 'phone', 'experience',
                  'level', 'CVs', 'status',
                  'vacancy', 'skype', 'position',
                  'interviews')
        depth = 3


class CandidateInterviewSerializer(serializers.ModelSerializer):
    """Candidate Serializer for Interiew List endpoint"""
    department = DepartmentSerializer(source='position.department')
    
    class Meta:
        model = Candidate
        fields = ('id', 'first_name', 'last_name', 'department')


class AuxCandidateSerializer(serializers.ModelSerializer):
    """Candidate Serializer for Candidates List and Candidate Detail endpoint"""

    class Meta:
        model = Candidate
        depth = 3
        fields = ('first_name', 'last_name', 'position')

