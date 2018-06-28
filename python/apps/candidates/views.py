from django.http import HttpResponse
from rest_framework import generics

from apps.candidates.models import Candidate
from apps.candidates.serializers import CandidateListSerializer


def test_func(request):
    return HttpResponse('Test')


class CandidateList(generics.ListAPIView):
    queryset = Candidate.objects.all()
    serializer_class = CandidateListSerializer
