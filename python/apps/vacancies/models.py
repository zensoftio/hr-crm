import uuid

from django.contrib.auth import get_user_model
from django.contrib.postgres.fields import ArrayField
from django.db import models
from django.urls import reverse

from apps.requests.models import Request

User = get_user_model()

EXPERIENCE = ((0, 'Без опыта'),
              (1, '1-3 лет'),
              (2, '3-6 лет'),
              (3, '6 лет и более'),)

WORKING_HOURS = ((0, 'Полный рабочий день'),
                 (1, 'Гибкий график'),
                 (2, 'Удаленная работа'),
                 )

EMPLOYMENT_TYPE = ((0, 'Полный рабочий день'),
                   (1, 'Не полный рабочий день'),
                   (2, 'Проект/временно'),
                   (3, 'Волонтерство'),
                   (4, 'Стажировка'))

VACANCY_STATUS = (
    (0, 'Не опубликовано'),
    (1, 'Опубликовано')
)


class Vacancy(models.Model):
    uuid = models.UUIDField(default=uuid.uuid4, editable=False, unique=True)
    title = models.CharField(max_length=200)
    request = models.ForeignKey(Request, on_delete=models.PROTECT)
    created_by = models.ForeignKey(User, on_delete=models.PROTECT)
    city = models.CharField(max_length=30)
    address = models.CharField(max_length=50)
    work_conditions = ArrayField(base_field=models.CharField(max_length=200, blank=True))
    experience = models.IntegerField(choices=EXPERIENCE, default=0)
    working_hours = models.IntegerField(choices=WORKING_HOURS, default=0)
    employment_type = models.IntegerField(choices=EMPLOYMENT_TYPE, default=0)
    salary_min = models.FloatField()
    salary_max = models.FloatField()
    image = models.ImageField(upload_to='media', null=True)
    responsibilities = models.TextField(null=True)
    created = models.DateTimeField(auto_now_add=True)
    last_published = models.DateTimeField(auto_now=True)
    status = models.IntegerField(choices=VACANCY_STATUS, default=0)

    class Meta:
        default_related_name = 'vacancies'
        verbose_name_plural = 'vacancies'

    def get_absolute_url(self):
        return reverse('vacancy-detail', kwargs={'pk': self.id})


class Publication(models.Model):
    vacancy = models.ForeignKey(Vacancy, on_delete=models.PROTECT)
    created_by = models.ForeignKey(User, on_delete=models.PROTECT)
    created = models.DateTimeField(auto_now_add=True)
    facebook = models.BooleanField()
    diesel = models.BooleanField()
    jobkg = models.BooleanField()

    class Meta:
        default_related_name = 'publications'

    def get_absolute_url(self):
        return reverse('publication-detail', kwargs={'pk': self.id})
