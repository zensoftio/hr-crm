from django.db import models

from apps.interview.models import Interview
from apps.users.models import User
from apps.department.models import Department


class Evaluation(models.Model):
    comment = models.TextField()
    interview = models.ForeignKey('Interview', on_delete=models.PROTECT)
    reviewer = models.ForeignKey('User', on_delete=models.PROTECT)

    def __str__(self):
        return self.commet


class Criteria(models.Model):
    name = models.CharField(max_length=100)
    department = models.ForeignKey('Department', on_delete=models.PROTECT)

    def __str__(self):
        return self.name
