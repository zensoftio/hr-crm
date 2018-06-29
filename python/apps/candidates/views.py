from rest_framework import generics, status

from .models import Candidate, Comment
from .serializers import AuxCandidateSerializer, CandidateDetailSerializer, CommentCreateSerializer
from rest_framework.response import Response


class CandidateListView(generics.ListAPIView):
    queryset = Candidate.objects.all()
    serializer_class = AuxCandidateSerializer


class CandidateDetailView(generics.RetrieveUpdateDestroyAPIView):
    queryset = Candidate.objects.all()
    serializer_class = CandidateDetailSerializer


class CommentCreateView(generics.CreateAPIView):
    queryset = Comment.objects.all()
    serializer_class = CommentCreateSerializer

    def create(self, request, *args, **kwargs):
        serializer = CommentCreateSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

