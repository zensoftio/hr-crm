from rest_framework.test import APITestCase

from apps.base_tests import ListCreateTestMixin
from .models import Department
from .serializers import DepartmentSerializer


class DepartmentListCreateTestCase(ListCreateTestMixin, APITestCase):
    url = '/departments/'
    model = Department
    request_body = {'name': 'Python'}
    serializer = DepartmentSerializer
