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
        "count": 3,
        "position": 2,
        "requirements": [
            1,
            3
        ],
        "status": 1,
        "created_by": 1
    }

    def test_creation(self):
        url = '/' + str(self.model._meta.verbose_name_plural) + '/'
        response = self.client.post(url, self.request_body)
        self.assertEqual(201, response.status_code)
        self.assertEqual(4, self.model.objects.count())


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
            "count": 2,
            "status": 1
        }