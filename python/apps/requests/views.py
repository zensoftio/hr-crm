from django.contrib.auth import get_user_model
from apps.requests.models import Request
from apps.requests.serializers import RequestSerializer, RequestPostSerializer
from rest_framework import generics, status
from rest_framework.views import APIView
from rest_framework.response import Response


User = get_user_model()


class RequestList(APIView):
    # queryset = Request.objects.all()
    # serializer_class = RequestSerializer

    # def perform_create(self, serializer):
    #     serializer.save()

    def get(self, request, format=None):
        requests = Request.objects.all()
        serializer = RequestSerializer(requests, many=True)
        return Response(serializer.data)

    def post(self, request, format=None):
        serializer = RequestPostSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


class RequestDetail(generics.RetrieveAPIView):

    queryset = Request.objects.all()
    serializer_class = RequestSerializer
