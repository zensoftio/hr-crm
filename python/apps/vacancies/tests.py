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
        url = '/api/v1/' + str(self.model._meta.verbose_name_plural)
        response = self.client.post(url, self.request_body)
        self.assertEqual(201, response.status_code)
        self.assertEqual(3, self.model.objects.count())

    fixtures = ['candidates.json', 'departments.json', 'requests.json', 'users.json', 'vacancies.json']

    request_body = {
        "uuid": "aada680d-c43b-413b-86db-b63da812832f",
        "title": "Django Developer Needed",
        "request": 2,
        "created_by": 1,
        "city": "Bishkek",
        "address": "Admin str. 123",
        "work_conditions": "[\"Test set of conditions\"]",
        "experience": 0,
        "working_hours": 0,
        "employment_type": 0,
        "salary_min": 200.0,
        "salary_max": 500.0,
        "image": "",
        "responsibilities": "qqweqweqweqweqweqweqwe",
        "created": "2018-07-09T08:05:58.278Z",
        "last_published": "2018-07-09T08:05:58.278Z",
        "status": 0
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