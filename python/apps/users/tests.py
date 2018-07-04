from django.contrib.auth import get_user_model
from rest_framework.test import APITestCase

from apps.base_tests import ListTestMixin
from apps.users.serializers import UserSerializer


class UsersListTest(APITestCase, ListTestMixin):
    url = '/users/'
    model = get_user_model()
    serializer = UserSerializer
