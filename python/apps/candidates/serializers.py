from rest_framework import serializers

from apps.departments.serializers import DepartmentSerializer, AuxPositionSerializer
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


class CommentCreateSerializer(serializers.ModelSerializer):
    class Meta:
        model = Comment
        fields = ('id', 'text', 'candidate', 'created_by', 'created')


class CandidateDetailSerializer(serializers.ModelSerializer):
    """Candidate serializer for Detailed Candidate endpoint"""
    interviews = AuxInterviewSerializer(many=True, read_only=True)
    CVs = CVSerializer(many=True, source='CV', read_only=True)
    comments = CommentSerializer(many=True, read_only=True)
    position = AuxPositionSerializer(partial=True)

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
