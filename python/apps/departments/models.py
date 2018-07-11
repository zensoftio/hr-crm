from django.db import models
from django.urls import reverse


REQUIREMENTS_STATUS = (
    ('REQUIRED', 'Обязательные'),
    ('OPTIONAL', 'Опциональные'),
    ('GENERAL', 'Общие'),
)


class Department(models.Model):
    name = models.CharField(max_length=200)
    protected = models.BooleanField(default=False)

    def __str__(self):
        return self.name

    def get_absolute_url(self):
        return reverse('v1:department-detail', kwargs={'pk': self.id})


class Requirement(models.Model):
    name = models.CharField(max_length=200)
    department = models.ForeignKey(Department, on_delete=models.PROTECT, related_name='requirements')
    type = models.CharField(choices=REQUIREMENTS_STATUS, max_length=100, default=0)

    def __str__(self):
        return self.name

    def get_absolute_url(self):
        return reverse('v1:requirement-detail', kwargs={'pk': self.id})


class Position(models.Model):
    department = models.ForeignKey(Department, on_delete=models.PROTECT, related_name='positions')
    name = models.CharField(max_length=200)

    def __str__(self):
        return self.name

    def get_absolute_url(self):
        return reverse('v1:position-detail', kwargs={'pk': self.id})
