from django.shortcuts import render
from rest_framework import generics
from django.http import HttpResponse

from apps.candidates.models import Candidate
from apps.candidates.serializers import CandidateSerializer

def test_func(request):
    return HttpResponse('Test')

class CandidateList(generics.ListAPIView):
    queryset = Candidate.objects.all()
    serializer_class = CandidateSerializer
