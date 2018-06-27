from django.shortcuts import render
from rest_framework import generics

from apps.candidates.models import Candidate
from apps.candidates.serializers import CandidateSerializer


class CandidateList(generics.ListAPIView):
    queryset = Candidate.objects.all()
    serializer_class = CandidateSerializer
