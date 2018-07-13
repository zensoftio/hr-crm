from django.contrib.auth import get_user_model
from django.db import models
from django.db.models.signals import post_save
from django.urls import reverse
from django.utils import timezone

from apps.departments.models import Position
from apps.notifications.notifications import candidate_created

User = get_user_model()

CANDIDATE_STATUS = (
    ('NOT_REVIEWED', 'На рассмотрений'),
    ('REVIEWED', 'Рассмотрено'),
    ('SATISFYING', 'Подходит'),
    ('NOT SATISFYING', 'Не подходит'),
    ('TEST SENT', 'Отправлен на тест'),
    ('INVITED_TO_INTERVIEW', 'Приглашен на интервью'),
    ('INTERVIEWS_CONDUCTED', 'Интервью проведено'),
    ('CURRENT_EMPLOYEE', 'Штат'),
    ('IN_RESERVE', 'Резерв'),
    ('INTERN', 'Стажер'),
    ('FAILED_INTERVIEW', 'Не прошел интервью'),
)


class Candidate(models.Model):
    first_name = models.CharField(max_length=50, blank=True, null=True)
    last_name = models.CharField(max_length=50, blank=True, null=True)
    email = models.EmailField(max_length=254)
    phone = models.CharField(max_length=30, blank=True, null=True)
    experience = models.FloatField(blank=True, null=True)
    level = models.CharField(max_length=30, blank=True, null=True)
    status = models.CharField(choices=CANDIDATE_STATUS, max_length=100, default=0)
    skype = models.CharField(max_length=254, blank=True, null=True)
    position = models.ForeignKey(Position, on_delete=models.PROTECT, null=True)
    created = models.DateTimeField(default=timezone.now)

    class Meta:
        default_related_name = 'candidates'
        verbose_name_plural = 'candidates'
        ordering = ('last_name',)

    def __str__(self):
        return self.email

    def get_absolute_url(self):
        return reverse('v1:candidate-detail', kwargs={'pk': self.id})


post_save.connect(candidate_created, sender=Candidate)


class CV(models.Model):
    url = models.URLField(null=True)
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
