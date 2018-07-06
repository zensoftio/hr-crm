from django.contrib.auth import get_user_model
from rest_framework import serializers

from apps.candidates.models import Candidate
from apps.users.serializers import AuxUserSerializer
from .models import Interview, Criteria, Interviewer, Evaluation

User = get_user_model()


class AuxCriteriaSerializer(serializers.ModelSerializer):
    class Meta:
        model = Criteria
        fields = ('id', 'name')


class CriteriaSerializer(serializers.ModelSerializer):
    class Meta:
        model = Criteria
        fields = ('id', 'name', 'department')


class AuxCandidateSerializer(serializers.ModelSerializer):
    """Candidate Serializer for Candidates List and Candidate Detail endpoint"""

    class Meta:
        model = Candidate
        depth = 3
        fields = ('id', 'first_name', 'last_name', 'position', 'status')


class EvaluationSerializer(serializers.ModelSerializer):
    criteria = AuxCriteriaSerializer()

    class Meta:
        model = Evaluation
        fields = ('id', 'rate', 'criteria')


class InterviewerSerializer(serializers.ModelSerializer):
    user = AuxUserSerializer()

    class Meta:
        model = Interviewer
        fields = ('id', 'user')


class InterviewerDetailSerializer(serializers.ModelSerializer):
    user = AuxUserSerializer()
    evaluations = EvaluationSerializer(many=True)

    class Meta:
        model = Interviewer
        fields = ('id', 'user', 'comment', 'evaluations')


class InterviewListSerializer(serializers.ModelSerializer):
    """Serializer for Interviews List Endpoint"""
    interviewers = InterviewerSerializer(many=True)
    candidate = AuxCandidateSerializer()
    filter_fields = ('status',)

    class Meta:
        model = Interview
        depth = 3
        fields = ('id', 'date', 'status', 'candidate', 'interviewers')


class InterviewCreateSerializer(serializers.ModelSerializer):
    interviewers = serializers.PrimaryKeyRelatedField(many=True, queryset=User.objects.all())

    class Meta:
        model = Interview
        fields = ('candidate', 'interviewers', 'date')

    def create(self, validated_data):
        users = validated_data.pop('interviewers')
        instance = Interview.objects.create(**validated_data)
        for user in users:
            Interviewer.objects.create(interview=instance, user=user)
        return instance

    def update(self, instance, validated_data):
        new_users = validated_data.pop('interviewers')
        old_users = [interviewer.user for interviewer in instance.interviewers.all()]

        for attr, value in validated_data.items():
            setattr(instance, attr, value)

        users_to_delete = set(old_users).difference(set(new_users))
        users_to_create = set(new_users).difference(set(old_users))

        Interviewer.objects.filter(interview=instance, user__in=users_to_delete).delete()
        interviewers_to_create = [Interviewer(interview=instance, user=user) for user in users_to_create]
        Interviewer.objects.bulk_create(interviewers_to_create)

        return instance


class InterviewDetailSerializer(serializers.ModelSerializer):
    candidate = AuxCandidateSerializer()
    interviewers = InterviewerDetailSerializer(many=True)

    class Meta:
        model = Interview
        fields = ('id', 'candidate', 'status', 'date', 'interviewers')


class AuxInterviewSerializer(serializers.ModelSerializer):
    """Serializer for Detailed Candidate Endpoint"""
    interviewers = InterviewerSerializer(many=True)

    class Meta:
        depth = 3
        model = Interview
        fields = ('id', 'status', 'date', 'interviewers')
