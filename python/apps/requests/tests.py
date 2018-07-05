from rest_framework.test import APITestCase

from apps.base_tests import ListTestMixin, CreateTestMixin, GetInstanceTestMixin
from .models import Request
from .serializer import RequestListSerializer, RequestDetailSerializer


class RequestListTestCase(ListTestMixin, APITestCase):
    url = '/requests/'
    model = Request
    serializer = RequestListSerializer


class RequestDetailTestCase(GetInstanceTestMixin, APITestCase):
    model = Request
    serializer = RequestDetailSerializer

    fixtures = ['candidates.json', 'departments.json', 'requests.json', 'users.json', 'vacancies.json',
                'interviews.json']

    def setUp(self):
        self.instance = Request.objects.get(pk=1)