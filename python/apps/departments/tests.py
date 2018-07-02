from rest_framework.test import APITestCase

from apps.base_tests import CreateTestMixin, ListTestMixin
from .models import Department
from .serializers import DepartmentSerializer


class DepartmentListCreateTestCase(ListTestMixin, CreateTestMixin, APITestCase):
    url = '/departments/'
    model = Department
    request_body = {'name': 'Python'}
    serializer = DepartmentSerializer
