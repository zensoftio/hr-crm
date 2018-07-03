from rest_framework import generics

from apps.interviews.models import Interview, Criteria
from apps.interviews.serializers import InterviewListSerializer, CriteriaSerializer, InterviewDetailSerializer


class InterviewListView(generics.ListAPIView):
    queryset = Interview.objects.all()
    serializer_class = InterviewListSerializer


class InterviewDetailView(generics.RetrieveAPIView):
    queryset = Interview.objects.all()
    serializer_class = InterviewDetailSerializer


class CriteriaCreateListView(generics.ListCreateAPIView):
    queryset = Criteria.objects.all()
    serializer_class = CriteriaSerializer
