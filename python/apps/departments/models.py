from django.db import models


class Department(models.Model):
    name = models.CharField(max_length=200)

    def __str__(self):
        return self.name


class Requirement(models.Model):
    name = models.CharField(max_length=200)
    department = models.ForeignKey(Department, on_delete=models.PROTECT)
    type = models.IntegerField()

    def __str__(self):
        return self.name


class Position(models.Model):
    department = models.ForeignKey(Department, on_delete=models.PROTECT)
    name = models.CharField(max_length=200)

    def __str__(self):
        return self.name
