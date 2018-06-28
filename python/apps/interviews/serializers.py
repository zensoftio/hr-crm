from rest_framework import serializers

from apps.candidates.serializers import CandidateInterviewSerializer
from apps.interviews.models import Interview
from apps.requests.serializer import RequestInterviewSerializer
from apps.users.serializers import UserInterviewSerializer


class InterviewListSerializer(serializers.ModelSerializer):
    candidate = CandidateInterviewSerializer()
    request = RequestInterviewSerializer()
    reviewers = UserInterviewSerializer(many=True)

    class Meta:
        model = Interview
        depth = 3
        fields = ('id', 'date', 'status', 'candidate', 'request', 'reviewers')
