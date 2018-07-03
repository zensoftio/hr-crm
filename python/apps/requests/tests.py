from rest_framework.test import APITestCase

from apps.base_tests import ListTestMixin, CreateTestMixin
from .models import Request
from .serializer import RequestListSerializer


class RequestListTestCase(ListTestMixin, APITestCase):
    url = '/requests/'
    model = Request
    serializer = RequestListSerializer
