from rest_framework.test import APITestCase

from apps.utils.base_tests import ListTestMixin
from .models import Request
from .serializer import RequestListSerializer


class RequestListTestCase(ListTestMixin, APITestCase):
    url = '/requests/'
    model = Request
    serializer = RequestListSerializer
