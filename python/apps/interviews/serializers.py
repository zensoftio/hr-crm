from rest_framework import serializers

from apps.users.serializers import AuxUserSerializer
from .models import Interview
from apps.candidates.models import Candidate


class AuxCandidateSerializer(serializers.ModelSerializer):
    """Candidate Serializer for Candidates List and Candidate Detail endpoint"""

    class Meta:
        fields = ('id', 'first_name', 'last_name', 'position')
        model = Candidate
        depth = 3


class InterviewListSerializer(serializers.ModelSerializer):
    """Serializer for Interviews List Endpoint"""
    candidate = AuxCandidateSerializer()
    interviewers = AuxUserSerializer(many=True)

    class Meta:
        model = Interview
        depth = 3
        fields = ('id', 'date', 'status', 'candidate', 'interviewers')


class AuxInterviewSerializer(serializers.ModelSerializer):
    """Serializer for Detailed Candidate Endpoint"""
    interviewers = AuxUserSerializer(many=True)

    class Meta:
        depth = 3
        model = Interview
        fields = ('id', 'status', 'date', 'interviewers')
