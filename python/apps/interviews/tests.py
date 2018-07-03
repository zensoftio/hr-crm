from rest_framework.test import APITestCase

from apps.base_tests import ListTestMixin, CreateTestMixin
from apps.departments.models import Department
from .models import Criteria
from .serializers import CriteriaSerializer


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
