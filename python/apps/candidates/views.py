from django.shortcuts import render

from candidates.models import Candidate
from candidates.serializers import CandidateSerializer
from rest_framework import generics


class CandidateList(generics.ListAPIView):
    queryset = Candidate.objects.all()
    serializer_class = CandidateSerializer
