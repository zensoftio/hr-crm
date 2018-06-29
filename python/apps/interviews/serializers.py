from rest_framework import serializers

from apps.requests.serializer import RequestInterviewSerializer

from apps.candidates.serializers import CandidateInterviewSerializer
from apps.interviews.models import Interview

from apps.users.serializers import UserInterviewSerializer
from .models import Interview


class InterviewListSerializer(serializers.ModelSerializer):
    """Serializer for Interviews List Endpoint"""
    candidate = 'apps.candidates.AuxCandidateSerializer()'
    interviewers = UserInterviewSerializer(many=True)

    class Meta:
        model = Interview
        depth = 3
        fields = ('id', 'date', 'status', 'candidate', 'request', 'interviewers')


class AuxInterviewSerializer(serializers.ModelSerializer):
    """Serializer for Detailed Candidate Endpoint"""

    class Meta:
        model = Interview
        fields = ('id', 'status', 'date')