from rest_framework import generics

from apps.interviews.models import Interview
from apps.interviews.serializers import InterviewListSerializer


class InterviewListView(generics.ListAPIView):
    queryset = Interview.objects.all()
    serializer_class = InterviewListSerializer


class InterviewDestroy(generics.RetrieveDestroyAPIView):
    queryset = Interview.objects.all()
    serializer_class = InterviewListSerializer
