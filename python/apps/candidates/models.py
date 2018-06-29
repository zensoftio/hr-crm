import datetime

from django.contrib.auth import get_user_model
from django.db import models
from django.utils import timezone

from apps.departments.models import Position
from apps.vacancies.models import Vacancy

User = get_user_model()


class Candidate(models.Model):
    first_name = models.CharField(max_length=50, blank=True, null=True)
    last_name = models.CharField(max_length=50, blank=True, null=True)
    email = models.EmailField(max_length=254)
    phone = models.CharField(max_length=30, blank=True, null=True)
    experience = models.FloatField(blank=True, null=True)
    level = models.CharField(max_length=30, blank=True, null=True)
    status = models.IntegerField(blank=True)
    vacancy = models.ForeignKey(Vacancy, on_delete=models.PROTECT)
    skype = models.CharField(max_length=254, blank=True, null=True)
    position = models.ForeignKey(Position, on_delete=models.PROTECT)
    created = models.DateTimeField(auto_now_add=True)

    class Meta:
        default_related_name = 'candidates'

    class Meta:
        default_related_name = 'candidates'

    def __str__(self):
        return '{0} {1}'.format(self.first_name, self.last_name)


class CV(models.Model):
    url = models.CharField(max_length=50, blank=True, null=True)
    candidate = models.ForeignKey(Candidate, on_delete=models.PROTECT, related_name='CV')
    created = models.DateTimeField(auto_now_add=True)

    def __str__(self):
        return '{0} {1}'.format(self.candidate, self.candidate.first_name)


class Comment(models.Model):
    text = models.TextField()
    created_by = models.ForeignKey(User, on_delete=models.PROTECT)
    created = models.DateTimeField(auto_now_add=True)
    candidate = models.ForeignKey(Candidate, on_delete=models.PROTECT)

    class Meta:
        default_related_name = 'comments'

    def __str__(self):
        return self.text
