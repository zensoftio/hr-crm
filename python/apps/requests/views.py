from django.contrib.auth import get_user_model
from apps.requests.models import Request
from apps.requests.serializer import RequestSerializer
from rest_framework import generics


User = get_user_model()


class RequestList(generics.ListCreateAPIView):

    queryset = Request.objects.all()
    serializer_class = RequestSerializer


class RequestDetail(generics.RetrieveUpdateAPIView):

    queryset = Request.objects.all()
    serializer_class = RequestSerializer
