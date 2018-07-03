from rest_framework import serializers

from apps.users.serializers import AuxUserSerializer
from .models import Interview, Criteria


class InterviewListSerializer(serializers.ModelSerializer):
    """Serializer for Interviews List Endpoint"""
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


class CriteriaSerializer(serializers.ModelSerializer):
    class Meta:
        model = Criteria
        fields = ('id', 'name', 'department')
