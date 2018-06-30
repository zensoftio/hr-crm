from rest_framework import serializers

from apps.departments.serializers import DepartmentSerializer, PositionSerializer, AuxPositionSerializer
from apps.interviews.serializers import AuxInterviewSerializer
from apps.users.serializers import AuxUserSerializer
from .models import Candidate, CV, Comment


class CVSerializer(serializers.ModelSerializer):
    class Meta:
        model = CV
        fields = ('id', 'url', 'created')


class CommentSerializer(serializers.ModelSerializer):
    created_by = AuxUserSerializer()

    class Meta:
        model = Comment
        fields = ('id', 'text', 'created_by', 'created')


class CandidateDetailSerializer(serializers.ModelSerializer):
    """Candidate serializer for Detailed Candidate endpoint"""
    interviews = AuxInterviewSerializer(many=True)
    CVs = CVSerializer(many=True, source='CV')
    comments = CommentSerializer(many=True)
    position = AuxPositionSerializer()

    class Meta:
        model = Candidate
        fields = ('id', 'first_name', 'last_name',
                  'email', 'phone', 'experience',
                  'level', 'CVs', 'status', 'skype', 'position',
                  'interviews', 'comments')


class CandidateInterviewSerializer(serializers.ModelSerializer):
    """Candidate Serializer for Interview List endpoint"""
    department = DepartmentSerializer(source='position.department')

    class Meta:
        model = Candidate
        fields = ('id', 'first_name', 'last_name', 'department')


class AuxCandidateSerializer(serializers.ModelSerializer):
    """Candidate Serializer for Candidates List and Candidate Detail endpoint"""

    class Meta:
        fields = ('id', 'first_name', 'last_name', 'position')
        model = Candidate
        depth = 3
