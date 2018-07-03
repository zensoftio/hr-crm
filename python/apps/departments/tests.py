from rest_framework.test import APITestCase

from apps.base_tests import CreateTestMixin, ListTestMixin
from .models import Department, Requirement, Position
from .serializers import DepartmentSerializer, RequirementSerializer, \
                                                            PositionSerializer


class DepartmentListCreateTestCase(ListTestMixin, CreateTestMixin, APITestCase):
    url = '/departments/'
    model = Department
    request_body = {'name': 'Python'}
    serializer = DepartmentSerializer


class RequirementListCreateTestCase(ListTestMixin, CreateTestMixin, APITestCase):
    url = '/requirements/'
    model = Requirement
    serializer = RequirementSerializer

    def setUp(self):
        department = Department.objects.create(name='Python')
        self.request_body = {
            'name': 'OOP',
            'department': department.id,
            'type': 0
        }


class PositionListCreateTestCase(ListTestMixin, CreateTestMixin, APITestCase):
    url = '/positions/'
    model = Position
    serializer = PositionSerializer

    def setUp(self):
        department = Department.objects.create(name='Python')
        self.request_body = {
            'department' : department.id,
            'name' : 'Python'
        }
