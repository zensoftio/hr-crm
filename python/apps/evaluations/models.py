from django.contrib.auth import get_user_model
from django.db import models

from apps.departments.models import Department
from apps.interviews.models import Interview

User = get_user_model()


class Evaluation(models.Model):
    comment = models.TextField()
    interview = models.ForeignKey(Interview, on_delete=models.PROTECT)
    reviewer = models.ForeignKey(User, on_delete=models.PROTECT)

    def __str__(self):
        return self.comment


class Criteria(models.Model):
    name = models.CharField(max_length=100)
    department = models.ForeignKey(Department, on_delete=models.PROTECT)

    def __str__(self):
        return self.name
