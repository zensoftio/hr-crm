from rest_framework import generics, status
from rest_framework.response import Response

from apps.utils.serializers import MethodSerializerView
from apps.interviews.models import Interview, Criteria
from apps.interviews.serializers import InterviewListSerializer, CriteriaListSerializer, InterviewDetailSerializer, \
    InterviewCreateSerializer, CriteriaCreateSerializer
from apps.users.permissions import IsInterviewer



class InterviewListCreateView(generics.ListCreateAPIView):
    queryset = Interview.objects.all()
    serializer_class = InterviewListSerializer
    filter_fields = ('status', 'candidate__position')

    def create(self, request, *args, **kwargs):
        write_serializer = InterviewCreateSerializer(data=request.data)
        write_serializer.is_valid(raise_exception=True)
        self.perform_create(write_serializer)

        read_serializer = InterviewDetailSerializer(write_serializer.instance)

        return Response(read_serializer.data, status=status.HTTP_201_CREATED)


class InterviewDetailView(generics.RetrieveUpdateAPIView):
    queryset = Interview.objects.all()
    serializer_class = InterviewDetailSerializer

    def partial_update(self, request, *args, **kwargs):
        partial = kwargs.pop('partial', True)
        instance = self.get_object()
        write_serializer = InterviewCreateSerializer(
            instance, data=request.data, partial=partial
        )
        write_serializer.is_valid(raise_exception=True)
        self.perform_update(write_serializer)

        if getattr(instance, '_prefetched_objects_cache', None):
            instance._prefetched_objects_cache = {}

        read_serializer = InterviewDetailSerializer(instance)

        return Response(read_serializer.data)


class CriteriaCreateListView(MethodSerializerView, generics.ListCreateAPIView):
    queryset = Criteria.objects.all()
    filter_fields = ('department',)

    method_serializer_classes = {
        ('GET',): CriteriaListSerializer,
        ('POST',): CriteriaCreateSerializer
    }
