from rest_framework.test import APITestCase

from apps.utils.base_tests import CreateTestMixin, ListTestMixin, GetInstanceTestMixin
from .models import Department, Requirement, Position
from .serializers import DepartmentSerializer, RequirementSerializer, \
                                                            PositionSerializer


class DepartmentListCreateTestCase(ListTestMixin, CreateTestMixin, APITestCase):
    model = Department
    request_body = {'name': 'Python'}
    serializer = DepartmentSerializer


class DepartmentDetailTestCase(GetInstanceTestMixin, APITestCase):
    model = Department
    serializer = DepartmentSerializer

    fixtures = ['candidates.json', 'departments.json', 'requests.json', 'users.json', 'vacancies.json',
                'interviews.json']

    def setUp(self):
        self.instance = Department.objects.get(pk=1)


class RequirementListCreateTestCase(ListTestMixin, CreateTestMixin, APITestCase):
    model = Requirement
    serializer = RequirementSerializer

    def setUp(self):
        department = Department.objects.create(name='Python')
        self.request_body = {
            'name': 'OOP',
            'department': department.id,
            'type': 0
        }


class RequirementDetailTestCase(GetInstanceTestMixin, APITestCase):
    model = Requirement
    serializer = RequirementSerializer

    fixtures = ['candidates.json', 'departments.json', 'requests.json', 'users.json', 'vacancies.json',
                'interviews.json']

    def setUp(self):
        self.instance = Requirement.objects.get(pk=1)


class PositionListCreateTestCase(ListTestMixin, CreateTestMixin, APITestCase):
    model = Position
    serializer = PositionSerializer

    def setUp(self):
        department = Department.objects.create(name='Python')
        self.request_body = {
            'department': department.id,
            'name': 'Python'
        }


class PositionDetailTestCase(GetInstanceTestMixin, APITestCase):
    model = Position
    serializer = PositionSerializer

    fixtures = ['candidates.json', 'departments.json', 'requests.json', 'users.json', 'vacancies.json',
                'interviews.json']

    def setUp(self):
        self.instance = Position.objects.get(pk=1)
