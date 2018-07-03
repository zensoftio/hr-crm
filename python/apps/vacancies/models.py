from django.contrib.auth import get_user_model
from django.db import models


from apps.departments.models import Requirement
from apps.requests.models import Request

User = get_user_model()

EXPERIENCE = (('0', 'Без опыта'),
              ('1-3', '1-3 лет'),
              ('3-6', '3-6 лет'),
              ('6+', '6 лет и более'),)

WORKING_HOURS = (('FT', 'Полный рабочий день'),
                 ('FLT', 'Гибкий график'),
                 ('RJ', 'Удаленная работа'),
                )

EMPLOYMENT_TYPE = (('FT', 'Полный рабочий день'),
                   ('PT', 'Не полный рабочий день'),
                   ('TMP', 'Проект/временно'),
                   ('VOL', 'Волонтерство'),
                   ('INT', 'Стажировка'))

VACANCY_STATUS = (
    (0, 'Не опубликовано'),
    (1, 'Опубликовано')
)


class Vacancy(models.Model):
    title = models.CharField(max_length=200)
    request = models.ForeignKey(Request, on_delete=models.PROTECT)
    created_by = models.ForeignKey(User, on_delete=models.PROTECT)
    city = models.CharField(max_length=30)
    address = models.CharField(max_length=50)
    work_conditions = models.CharField(max_length=200, blank=True)
    experience = models.CharField(choices=EXPERIENCE, max_length=3, default='0')
    working_hours = models.CharField(choices=WORKING_HOURS, max_length=3, default='FLT')
    employment_type = models.CharField(choices=EMPLOYMENT_TYPE, max_length=3, default='FT')
    salary_min = models.FloatField()
    salary_max = models.FloatField()
    image = models.ImageField(null=True, blank=True)
    created = models.DateTimeField(auto_now_add=True)
    last_published = models.DateTimeField(auto_now=True)
    status = models.IntegerField(choices=VACANCY_STATUS, default=0)
    requirements = models.ManyToManyField(Requirement)

    class Meta:
        default_related_name = 'vacancies'


class Publication(models.Model):
    vacancy = models.ForeignKey(Vacancy, on_delete=models.PROTECT)
    created_by = models.ForeignKey(User, on_delete=models.PROTECT)
    created = models.DateTimeField(auto_now_add=True)
    facebook = models.BooleanField()
    diesel = models.BooleanField()
    jobkg = models.BooleanField()

    class Meta:
        default_related_name = 'publications'
