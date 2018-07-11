from rest_framework.test import APITestCase

from apps.utils.base_tests import ListTestMixin, CreateTestMixin, GetInstanceTestMixin, UpdateTestMixin
from .models import Request
from .serializer import RequestListSerializer, RequestDetailSerializer, RequestCreateOrUpdateSerializer


class RequestListTestCase(ListTestMixin, APITestCase):
    model = Request
    serializer = RequestListSerializer

    fixtures = ['candidates.json', 'departments.json', 'requests.json', 'users.json', 'vacancies.json',
                'interviews.json']


class RequestCreateTestCase(CreateTestMixin, APITestCase):
    model = Request
    serializer = RequestCreateOrUpdateSerializer

    fixtures = ['candidates.json', 'departments.json', 'requests.json', 'users.json', 'vacancies.json',
                'interviews.json']

    request_body = {
        'count': 3,
        'position': 2,
        'requirements': [
            1,
            3
        ],
        'status': "NOT_REVIEWED",
        'created_by': 1
    }


class RequestDetailTestCase(GetInstanceTestMixin, APITestCase):
    model = Request
    serializer = RequestDetailSerializer

    fixtures = ['candidates.json', 'departments.json', 'requests.json', 'users.json', 'vacancies.json',
                'interviews.json']

    def setUp(self):
        self.instance = Request.objects.get(pk=1)


class RequestUpdateTestCase(UpdateTestMixin, APITestCase):
    model = Request
    serializer = RequestCreateOrUpdateSerializer

    fixtures = ['candidates.json', 'departments.json', 'requests.json', 'users.json', 'vacancies.json',
                'interviews.json']

    def setUp(self):
        self.instance = Request.objects.get(pk=1)
        self.update_data = {
            'count': 2,
            'status': "APPROVED"
        }