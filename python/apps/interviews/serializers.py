from rest_framework import serializers

from apps.users.serializers import AuxUserSerializer
from .models import Interview


class InterviewListSerializer(serializers.ModelSerializer):
    """Serializer for Interviews List Endpoint"""
    candidate = 'apps.candidates.AuxCandidateSerializer()'
    interviewers = AuxUserSerializer(many=True)

    class Meta:
        model = Interview
        depth = 3
        fields = ('id', 'date', 'status', 'candidate', 'request', 'interviewers')


class AuxInterviewSerializer(serializers.ModelSerializer):
    """Serializer for Detailed Candidate Endpoint"""
    interviewers = AuxUserSerializer(many=True)

    class Meta:
        depth = 3
        model = Interview
        fields = ('id', 'status', 'date', 'interviewers')
