from django.contrib.auth import get_user_model
from rest_framework import generics, status
from rest_framework.response import Response
from rest_framework.views import APIView

from apps.requests.models import Request
from apps.requests.serializers import RequestSerializer, RequestPostSerializer

User = get_user_model()


class RequestList(generics.ListCreateAPIView):
    queryset = Request.objects.all()
    serializer_class = RequestSerializer

    def create(self, request):
        serializer = RequestPostSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.data, status=status.HTTP_400_BAD_REQUEST)

    # def get(self, request, format=None):
    #     requests = Request.objects.all()
    #     serializer = RequestSerializer(requests, many=True)
    #     return Response(serializer.data)
    #
    # def post(self, request, format=None):
    #     serializer = RequestPostSerializer(data=request.data)
    #     if serializer.is_valid():
    #         serializer.save()
    #         return Response(serializer.data, status=status.HTTP_201_CREATED)
    #     return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


class RequestDetail(generics.RetrieveAPIView):
    queryset = Request.objects.all()
    serializer_class = RequestSerializer
