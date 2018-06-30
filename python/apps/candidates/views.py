from django.http import HttpResponse
from rest_framework import generics
from rest_framework.response import Response

from apps.departments.models import Position
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

    def update(self, request, *args, **kwargs):
        position = request.data.get('position')
        if position:
            position = Position.objects.get(pk=position)
            candidate = Candidate.objects.get(pk=kwargs.get('pk'))
            candidate.position = position
            candidate.save()
            serializer = CandidateDetailSerializer(candidate)
            return Response(serializer.data)
        else:
            return super().update(request, *args, **kwargs)
