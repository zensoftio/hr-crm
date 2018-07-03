from rest_framework import serializers

from apps.candidates.models import Candidate
from apps.users.serializers import AuxUserSerializer
from .models import Interview, Criteria


class AuxCandidateSerializer(serializers.ModelSerializer):
    """Candidate Serializer for Candidates List and Candidate Detail endpoint"""

    class Meta:
        model = Candidate
        depth = 3
        fields = ('id', 'first_name', 'last_name', 'position')


class InterviewListSerializer(serializers.ModelSerializer):
    """Serializer for Interviews List Endpoint"""
    interviewers = AuxUserSerializer(many=True)
    candidate = AuxCandidateSerializer()

    class Meta:
        model = Interview
        depth = 3
        fields = ('id', 'date', 'status', 'candidate', 'interviewers')


class InterviewDetailSerializer(serializers.ModelSerializer):
    class Meta:
        model = Interview
        fields = '__all__'


class AuxInterviewSerializer(serializers.ModelSerializer):
    """Serializer for Detailed Candidate Endpoint"""
    interviewers = AuxUserSerializer(many=True)

    class Meta:
        depth = 3
        model = Interview
        fields = ('id', 'status', 'date', 'interviewers')


class CriteriaSerializer(serializers.ModelSerializer):
    class Meta:
        model = Criteria
        fields = ('id', 'name', 'department')

