from django.contrib.auth.models import AbstractBaseUser, PermissionsMixin, Group
from django.db import models
from django.urls import reverse

from apps.departments.models import Department
from .managers import UserManager


class User(AbstractBaseUser, PermissionsMixin):
    first_name = models.CharField(max_length=25, blank=True)
    last_name = models.CharField(max_length=25, blank=True)
    email = models.CharField(max_length=100, unique=True)  # validators=[email_validator] ### For future authentication
    created = models.DateTimeField(auto_now_add=True)
    is_active = models.BooleanField(default=True)
    is_staff = models.BooleanField(default=False)
    departments = models.ManyToManyField(Department)

    objects = UserManager()

    USERNAME_FIELD = 'email'
    REQUIRED_FIELDS = []

    def get_full_name(self):
        return '{0} {1}'.format(self.first_name, self.last_name).strip()

    def get_short_name(self):
        return self.last_name

    def get_absolute_url(self):
        return reverse('v1:user-detail', kwargs={'pk': self.id})
