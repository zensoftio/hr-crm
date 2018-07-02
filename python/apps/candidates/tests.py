from django.contrib.auth import get_user_model
from django.utils import timezone
from rest_framework.test import APITestCase

from apps.base_tests import ListTestMixin, GetInstanceTestMixin
from apps.candidates.models import Candidate
from apps.candidates.serializers import AuxCandidateSerializer, CandidateDetailSerializer
from apps.departments.models import Position, Department

User = get_user_model()


class CandidatesListTest(APITestCase, ListTestMixin):
    url = '/candidates/'
    model = Candidate
    serializer = AuxCandidateSerializer


class CandidatesDetailTest(APITestCase, GetInstanceTestMixin):
    url = '/candidates/'
    model = Candidate
    serializer = CandidateDetailSerializer

    def setUp(self):
        self.instance = Candidate.objects.create(
            first_name='Test First Name',
            last_name='Test Last Name',
            email='second@zensoft.io',
            phone='+996123123123',
            experience=2,
            level='Junior',
            status=1,
            skype='test.skype.com',
            position=Position.objects.create(
                name='Elixir Developer',
                department=Department.objects.create(
                    name='Elixir'
                )
            ),
            created=timezone.now()
        )
