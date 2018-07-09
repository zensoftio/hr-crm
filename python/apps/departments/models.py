from django.db import models
from django.urls import reverse


class Department(models.Model):
    name = models.CharField(max_length=200)

    def __str__(self):
        return self.name

    def get_absolute_url(self):
        return reverse('department-detail', kwargs={'pk': self.id})


class Requirement(models.Model):
    name = models.CharField(max_length=200)
    department = models.ForeignKey(Department, on_delete=models.PROTECT, related_name='requirements')
    type = models.IntegerField()

    def __str__(self):
        return self.name

    def get_absolute_url(self):
        return reverse('requirement-detail', kwargs={'pk': self.id})


class Position(models.Model):
    department = models.ForeignKey(Department, on_delete=models.PROTECT, related_name='positions')
    name = models.CharField(max_length=200)

    def __str__(self):
        return self.name

    def get_absolute_url(self):
        return reverse('position-detail', kwargs={'pk': self.id})