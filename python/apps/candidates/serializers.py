from rest_framework import serializers

from apps.candidates.models import Candidate
from apps.departments.serializers import PositionSerializer


class CandidateSerializer(serializers.ModelSerializer):
    class Meta:
        model = Candidate
        fields = ('first_name', 'last_name', 'email', 'phone', 'experience',
                  'level', 'status', 'vacancy', 'skype', 'position',)
        depth = 3


class CandidateInterviewSerializer(serializers.ModelSerializer):
    position = PositionSerializer()

    class Meta:
        model = Candidate
        fields = ('id', 'first_name', 'last_name', 'position')


class CandidateListSerializer(serializers.ModelSerializer):
    class Meta:
        model = Candidate
        depth = 2
        fields = ('first_name', 'last_name', 'position','status', 'created')
