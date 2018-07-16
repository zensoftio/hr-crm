from django.contrib.auth import get_user_model
from rest_framework import generics, status
from rest_framework.response import Response

from apps.requests.models import Request
from apps.requests.serializer import RequestListSerializer, RequestDetailSerializer, \
    RequestCreateOrUpdateSerializer

User = get_user_model()


class RequestListCreateView(generics.ListCreateAPIView):
    queryset = Request.objects.all()
    serializer_class = RequestListSerializer
    filter_fields = ('position__department', 'status')

    def create(self, request, *args, **kwargs):
        write_serializer = RequestCreateOrUpdateSerializer(data=request.data)
        write_serializer.is_valid(raise_exception=True)
        self.perform_create(write_serializer)

        read_serializer = RequestDetailSerializer(write_serializer.instance)

        return Response(read_serializer.data, status=status.HTTP_201_CREATED)


class RequestDetail(generics.RetrieveUpdateAPIView):
    queryset = Request.objects.all()
    serializer_class = RequestDetailSerializer

    def partial_update(self, request, *args, **kwargs):
        partial = kwargs.pop('partial', True)
        instance = self.get_object()
        write_serializer = RequestCreateOrUpdateSerializer(
            instance, data=request.data, partial=partial
        )
        write_serializer.is_valid(raise_exception=True)
        self.perform_update(write_serializer)

        if getattr(instance, '_prefetched_objects_cache', None):
            instance._prefetched_objects_cache = {}

        read_serializer = RequestDetailSerializer(instance)

        return Response(read_serializer.data)
