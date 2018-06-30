from django.contrib.auth import get_user_model
from rest_framework import generics, status
from rest_framework.response import Response

from apps.requests.models import Request
from apps.requests.serializers import RequestSerializer, RequestPostSerializer, RequestCreatedSerializer

User = get_user_model()


class RequestList(generics.ListCreateAPIView):
    queryset = Request.objects.all()
    serializer_class = RequestSerializer

    def create(self, request):
        write_serializer = RequestPostSerializer(data=request.data)
        write_serializer.is_valid(raise_exception=True)
        self.perform_create(write_serializer)
        read_serializer = RequestCreatedSerializer(write_serializer.instance)
        return Response(read_serializer.data, status=status.HTTP_201_CREATED)


class RequestDetail(generics.RetrieveAPIView):
    queryset = Request.objects.all()
    serializer_class = RequestSerializer
