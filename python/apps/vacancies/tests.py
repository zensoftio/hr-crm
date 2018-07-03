from rest_framework.test import APITestCase

from apps.base_tests import ListTestMixin, VacancyCreateTestMixin
from .models import Vacancy
from .serializers import VacancyListSerializer, VacancyCreateUpdateSerializer


class VacancyListTestCase(ListTestMixin, APITestCase):
    url = '/vacancies/'
    model = Vacancy
    serializer = VacancyListSerializer


class VacancyCreateListTestCase(VacancyCreateTestMixin, APITestCase):
    url = '/vacancies/'
    model = Vacancy
    serializer = VacancyCreateUpdateSerializer

    fixtures = ['candidates.json', 'departments.json', 'requests.json', 'users.json', 'vacancies.json']

    request_body = {
        "title": "Interns required",
        "request": 1,
        "created_by": 1,
        "city": "Bishkek",
        "address": "Bishkek",
        "work_conditions": "Nice kitchen",
        "experience": "0",
        "working_hours": "FT",
        "employment_type": "FT",
        "salary_min": 200.0,
        "salary_max": 1000.0,
        "created": "2018-05-21T10:35:18+0000",
        "last_published": "2018-05-21T10:35:18+0000",
        "status": 1,
        "requirements": [
            7,
            8
        ]
    }
