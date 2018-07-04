from django.contrib.auth import get_user_model
from django.db import models
from django.utils import timezone

from apps.departments.models import Position

User = get_user_model()

CANDIDATE_STATUS = (
    (0, 'Не рассмотрено'),
    (1, 'Рассмотрено'),
    (2, 'Подходит'),
    (3, 'Не подходит'),
    (4, 'Отправлен тест'),
    (5, 'Приглашен на интервью'),
    (6, 'Интервью проведено'),
    (7, 'Штат'),
    (8, 'Резерв'),
    (9, 'Стажёр'),
    (10, 'Не прошел интервью'),
)


class Candidate(models.Model):
    first_name = models.CharField(max_length=50, blank=True, null=True)
    last_name = models.CharField(max_length=50, blank=True, null=True)
    email = models.EmailField(max_length=254)
    phone = models.CharField(max_length=30, blank=True, null=True)
    experience = models.FloatField(blank=True, null=True)
    level = models.CharField(max_length=30, blank=True, null=True)
    status = models.IntegerField(choices=CANDIDATE_STATUS, default=0)
    skype = models.CharField(max_length=254, blank=True, null=True)
    position = models.ForeignKey(Position, on_delete=models.PROTECT, null=True)
    created = models.DateTimeField(default=timezone.now)

    class Meta:
        default_related_name = 'candidates'
        verbose_name_plural = 'candidates'
        ordering = ('last_name',)

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
