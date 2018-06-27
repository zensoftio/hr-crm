from django.db import models

from apps.users.models import User
from apps.departments.models import Position
from apps.vacancies.models import Vacancy


class Candidate(models.Model):
    first_name = models.CharField(max_length=50, blank=True, default='')
    last_name = models.CharField(max_length=50, blank=True, default='')
    email = models.EmailField(max_length=254)
    phone = models.CharField(max_length=30, blank=True, default='')
    experience = models.IntegerField(blank=True)
    level = models.CharField(max_length=30, blank=True)
    status = models.IntegerField(blank=True)
    vacancy = models.ForeignKey('Vacancy', on_delete=models.PROTECT)
    skype = models.CharField(max_length=254, blank=True, default='')
    position = models.ForeignKey('Position', on_delete=models.PROTECT)

    def __str__(self):
        return '{0} {1}'.format(self.first_name, self.last_name)


class Cv(models.Model):
    url = models.TextField(blank=True)
    candidate = models.ForeignKey('Candidate', on_delete=models.PROTECT)
    created = models.DateTimeField(auto_now_add=True)

    def __str__(self):
        return '{0} {1}'.format(self.candidate, self.candidate.first_name)


class Comment(models.Model):
    text = models.TextField()
    created_by = models.ForeignKey('User', on_delete=models.PROTECT)
    created = models.DateTimeField(auto_now_add=True)
    candidate = models.ForeignKey('Candidate', on_delete=models.PROTECT)

    def __str__(self):
        return self.text
