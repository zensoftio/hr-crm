from django.contrib.auth import get_user_model
from django.db import models

from apps.requests.models import Request

User = get_user_model()

EXPERIENCE = (('0', 'No-experience'),
              ('1-3', '1-3 years'),
              ('3-6', '3-6 years'),
              ('6+', '6 or more'),)

WORKING_HOURS = (('FT', 'full-time-day'),
                 ('RS', 'rotation_shift'),
                 ('FLT', 'flexible-time'),
                 ('RJ', 'remote-job'),
                 ('SF', 'special-shift-day'),)

EMPLOYMENT_TYPE = (('FT', 'full-time'),
                   ('PT', 'part-time'),
                   ('TMP', 'project/temporary'),
                   ('VOL', 'volunteer'),
                   ('INT', 'internship'))

VACANCY_STATUS = (
    (0, 'Unpublished'),
    (1, 'Published')
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

    class Meta:
        default_related_name = 'vacancies'


class Publication(models.Model):
    vacancy = models.ForeignKey(Vacancy, on_delete=models.PROTECT)
    created = models.DateTimeField(auto_now_add=True)
    created_by = models.ForeignKey(User, on_delete=models.PROTECT)
    facebook = models.BooleanField()
    diesel = models.BooleanField()
    jobkg = models.BooleanField()

    class Meta:
        default_related_name = 'publications'
