from rest_framework.test import APITestCase
from django.utils import timezone

from apps.base_tests import CreateTestMixin, ListTestMixin
from apps.requests.models import Request
from apps.departments.models import Position, Department
from apps.users.models import User
from .models import Vacancy
from .serializers import VacancyListSerializer, VacancyCreateUpdateSerializer


class VacancyCreateListTestCase(ListTestMixin, APITestCase):
    url = '/vacancies/'
    model = Vacancy
    serializer = VacancyListSerializer
