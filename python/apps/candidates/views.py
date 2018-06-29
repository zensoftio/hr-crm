from django.http import HttpResponse
from rest_framework import generics

from .models import Candidate
from .serializers import AuxCandidateSerializer, CandidateDetailSerializer


def test_func(request):
    return HttpResponse('Test')


class CandidateListView(generics.ListAPIView):
    queryset = Candidate.objects.all()
    serializer_class = AuxCandidateSerializer


class CandidateDetailView(generics.RetrieveUpdateDestroyAPIView):
    queryset = Candidate.objects.all()
    serializer_class = CandidateDetailSerializer
