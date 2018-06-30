from django.contrib.auth import get_user_model
from apps.requests.models import Request
from apps.requests.serializer import RequestListSerializer
from rest_framework import generics


User = get_user_model()


class RequestListCreateView(generics.ListCreateAPIView):

    queryset = Request.objects.all()
    serializer_class = RequestListSerializer


class RequestDetail(generics.RetrieveUpdateAPIView):

    queryset = Request.objects.all()
    serializer_class = RequestListSerializer
