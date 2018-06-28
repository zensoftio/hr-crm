from rest_framework import serializers
from apps.candidates.models import Candidate


class CandidateSerializer(serializers.ModelSerializer):
    class Meta:
        model = Candidate
        fields = ('first_name', 'last_name', 'email', 'phone', 'experience',
                  'level', 'status', 'vacancy', 'skype', 'position',)
        depth = 3


class CandidateListItemSerializer(serializers.ModelSerializer):

    class Meta:
        model = Candidate
        depth = 3
        fields = ('first_name', 'last_name', 'position')
