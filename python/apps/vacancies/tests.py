from rest_framework.test import APITestCase

from .models import Vacancy, Publication
from .serializers import VacancyListSerializer, VacancyCreateUpdateSerializer, VacancyDetailSerializer, \
                                                                                                PublicationSerializer
from apps.utils.base_tests import ListTestMixin, CreateTestMixin, GetInstanceTestMixin



class VacancyListTestCase(ListTestMixin, APITestCase):
    model = Vacancy
    serializer = VacancyListSerializer


class VacancyCreateTestCase(CreateTestMixin, APITestCase):
    model = Vacancy
    serializer = VacancyCreateUpdateSerializer

    def test_creation(self):
        url = '/' + str(self.model._meta.verbose_name_plural) + '/'
        response = self.client.post(url, self.request_body)
        self.assertEqual(201, response.status_code)
        self.assertEqual(3, self.model.objects.count())

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


class VacancyDetailTestCase(GetInstanceTestMixin, APITestCase):
    model = Vacancy
    serializer = VacancyDetailSerializer

    fixtures = ['candidates.json', 'departments.json', 'requests.json', 'users.json', 'vacancies.json',
                'interviews.json']

    def setUp(self):
        self.instance = Vacancy.objects.get(pk=1)


class PublicationDetailTestCase(GetInstanceTestMixin, APITestCase):
    model = Publication
    serializer = PublicationSerializer

    fixtures = ['candidates.json', 'departments.json', 'requests.json', 'users.json', 'vacancies.json',
                'interviews.json']

    def setUp(self):
        self.instance = Publication.objects.get(pk=1)