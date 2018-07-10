from rest_framework.test import APITestCase

from apps.utils.base_tests import ListTestMixin, CreateTestMixin, GetInstanceTestMixin
from apps.departments.models import Department
from .models import Criteria, Interview
from .serializers import CriteriaSerializer, InterviewDetailSerializer, InterviewListSerializer, \
                                                                                            InterviewCreateSerializer


class CriteriaCreateListTestCase(ListTestMixin, CreateTestMixin, APITestCase):
    model = Criteria
    serializer = CriteriaSerializer

    fixtures = ['candidates.json', 'departments.json', 'requests.json', 'users.json', 'vacancies.json',
                'interviews.json']

    request_body = {
        "name": "OOP",
        "department": 1
    }


class InterviewListTestCase(ListTestMixin, APITestCase):
    model = Interview
    serializer = InterviewListSerializer

    fixtures = ['candidates.json', 'departments.json', 'requests.json', 'users.json', 'vacancies.json',
                'interviews.json']


class InterviewCreateTestCase(CreateTestMixin, APITestCase):
    model = Interview
    serializer = InterviewCreateSerializer

    fixtures = ['candidates.json', 'departments.json', 'requests.json', 'users.json', 'vacancies.json',
                'interviews.json']

    def test_creation(self):
        url = '/' + str(self.model._meta.verbose_name_plural) + '/'
        response = self.client.post(url, self.request_body)
        self.assertEqual(201, response.status_code)
        self.assertEqual(4, self.model.objects.count())

    request_body = {
        "date": "2020-11-22T19:53:42",
        "status": 1,
        "candidate": 1
        }


class InterviewDetailTestCase(GetInstanceTestMixin, APITestCase):
    model = Interview
    serializer = InterviewDetailSerializer

    fixtures = ['candidates.json', 'departments.json', 'requests.json', 'users.json', 'vacancies.json',
                'interviews.json']

    def setUp(self):
        self.instance = Interview.objects.get(pk=1)