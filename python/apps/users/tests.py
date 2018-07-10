from django.contrib.auth import get_user_model
from rest_framework.test import APITestCase

from apps.utils.base_tests import ListTestMixin, GetInstanceTestMixin
from apps.users.serializers import UserSerializer
from .models import User


class UsersListTest(APITestCase, ListTestMixin):
    model = get_user_model()
    serializer = UserSerializer


class UserDetailTest(APITestCase, GetInstanceTestMixin):
    model = User
    serializer = UserSerializer

    fixtures = ['candidates.json', 'departments.json', 'requests.json', 'users.json', 'vacancies.json',
                'interviews.json']

    def setUp(self):
        self.instance = User.objects.get(pk=1)