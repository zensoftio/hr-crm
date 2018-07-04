from django.contrib.auth import get_user_model
from django.db import models

from apps.candidates.models import Candidate
from apps.departments.models import Department

User = get_user_model()

INTERVIEW_STATUS = (
    (0, 'Предстоит'),
    (1, 'Прошло'),
    (2, 'Отменено'),
)


class Interview(models.Model):
    date = models.DateTimeField()
    status = models.IntegerField(default=1, choices=INTERVIEW_STATUS)
    candidate = models.ForeignKey(Candidate, on_delete=models.PROTECT, related_name='interviews')

    def __str__(self):
        return '{name} {date}'.format(date=self.date, name=self.candidate.first_name)


class Interviewer(models.Model):
    comment = models.TextField(null=True, blank=True)
    interview = models.ForeignKey(Interview, related_name='interviewers', on_delete=models.PROTECT)
    user = models.ForeignKey(User, related_name='interviews', on_delete=models.PROTECT)

    def __str__(self):
        return '{interview}'.format(interview=self.interview)


class Evaluation(models.Model):
    interviewer = models.ForeignKey(Interviewer, on_delete=models.PROTECT)
    rate = models.IntegerField()
    criteria = models.ForeignKey('Criteria', on_delete=models.PROTECT)

    class Meta:
        default_related_name = 'evaluations'


class Criteria(models.Model):
    name = models.CharField(max_length=100)
    department = models.ForeignKey(Department, on_delete=models.PROTECT)

    def __str__(self):
        return self.name
