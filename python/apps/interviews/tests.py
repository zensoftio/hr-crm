from rest_framework.test import APITestCase

from apps.base_tests import ListTestMixin, CreateTestMixin, GetInstanceTestMixin
from apps.departments.models import Department
from .models import Criteria, Interview
from .serializers import CriteriaSerializer, InterviewDetailSerializer, InterviewListSerializer


class CriteriaCreateListTestCase(ListTestMixin, CreateTestMixin, APITestCase):
    url = '/criterias/'
    model = Criteria
    serializer = CriteriaSerializer

    def setUp(self):
        department = Department.objects.create(name='Python')
        self.request_body = {
            "name": "OOP",
            "department": department.id
        }


class InterviewListTestCase(ListTestMixin, APITestCase):
    url = '/interviews/'
    model = Interview
    serializer = InterviewListSerializer


class InterviewDetailTestCase(GetInstanceTestMixin, APITestCase):
    model = Interview
    serializer = InterviewDetailSerializer

    fixtures = ['candidates.json', 'departments.json', 'requests.json', 'users.json', 'vacancies.json',
                'interviews.json']

    def setUp(self):
        self.instance = Interview.objects.get(pk=1)