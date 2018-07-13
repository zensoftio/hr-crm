from rest_framework.test import APITestCase
from django.utils import timezone

from apps.utils.base_tests import ListTestMixin, CreateTestMixin, GetInstanceTestMixin
from .models import Criteria, Interview
from .serializers import CriteriaCreateSerializer, InterviewDetailSerializer, InterviewListSerializer, \
    CriteriaListSerializer, InterviewCreateSerializer


class CriteriaCreateTestCase(CreateTestMixin, APITestCase):
    model = Criteria
    serializer = CriteriaCreateSerializer

    fixtures = ['candidates.json', 'departments.json', 'requests.json', 'users.json', 'vacancies.json',
                'interviews.json']

    request_body = {
        "name": "OOP",
        "department": 1
    }


class CriteriaListTestCase(ListTestMixin, APITestCase):
    model = Criteria
    serializer = CriteriaListSerializer

    fixtures = ['candidates.json', 'departments.json', 'requests.json', 'users.json', 'vacancies.json',
                'interviews.json']


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

    request_body = {
        "date": timezone.now(),
        "status": 1,
        "interviewers": [1, 2],
        "candidate": 1
        }


class InterviewDetailTestCase(GetInstanceTestMixin, APITestCase):
    model = Interview
    serializer = InterviewDetailSerializer

    fixtures = ['candidates.json', 'departments.json', 'requests.json', 'users.json', 'vacancies.json',
                'interviews.json']

    def setUp(self):
        self.instance = Interview.objects.get(pk=1)