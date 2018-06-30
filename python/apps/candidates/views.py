
from django.http import HttpResponse

from rest_framework import generics, status
from rest_framework.response import Response

from apps.departments.models import Position
from .models import Candidate, Comment
from .serializers import AuxCandidateSerializer, CandidateDetailSerializer, CommentCreateSerializer


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


class CommentCreateView(generics.CreateAPIView):
    queryset = Comment.objects.all()
    serializer_class = CommentCreateSerializer

    def create(self, request, *args, **kwargs):
        serializer = CommentCreateSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)
