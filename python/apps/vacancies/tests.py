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

    fixtures = ['candidates.json', 'departments.json', 'requests.json', 'users.json', 'interviews.json']

    request_body = {
        "uuid": "aada680d-c43b-413b-86db-b63da812832fwedw",
        "title": "Django Developer Needed",
        "request": 1,
        "created_by": 1,
        "city": "Bishkek",
        "address": "Admin str. 2",
        "work_conditions": ["Test set of conditions", "FFFFF"],
        "working_hours": 0,
        "salary_min": 200.0,
        "salary_max": 500.0,
        "responsibilities": "qqweqweqweqweqweqweqwe",
        "comments": "Comment 3",
        "created": "2018-07-09T08:05:58.278Z",
        "last_published": "2018-07-09T08:05:58.278Z"
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