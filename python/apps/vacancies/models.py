from django.contrib.auth import get_user_model
from django.contrib.postgres.fields import ArrayField
from django.db import models

from apps.departments.models import Position
from apps.requests.models import Request

User = get_user_model()

HH_TIER_TYPE = (('PR', 'premium'), ('ST+', 'standard+'),
                ('ST', 'standard'),
                ('FR', 'free'))

EXPERIENCE = (('0', 'No-experience'),
              ('1-3', '1-3 years'),
              ('3-6', '3-6 years'),
              ('6+', '6 or more'),)

WORKING_HOURS = (('FT', 'full-time-day'),
                 ('RS', 'rotation_shift'),
                 ('FT', 'flexible-time'),
                 ('RJ', 'remote-job'),
                 ('SF', 'special-shift-day'),)

EMPLOYMENT_PATTERNS = (('FT', 'full-time'),
                       ('PT', 'part-time'),
                       ('TMP', 'project/temporary'),
                       ('VOL', 'volunteer'),
                       ('INT', 'internship'))


class Vacancy(models.Model):
    name = models.CharField(max_length=200)
    created = models.DateTimeField(auto_now_add=True)
    modified = models.DateTimeField(auto_now=True)
    topic = models.CharField(max_length=200)
    city = models.CharField(max_length=30)
    address = models.CharField(max_length=200)
    hh_payment_type = models.CharField(choices=HH_TIER_TYPE,
                                       max_length=3,
                                       default='FR')
    work_conditions = ArrayField(
        models.CharField(max_length=100,
                         blank=True),
        size=15
    )
    experience = models.CharField(choices=EXPERIENCE,
                                  max_length=3,
                                  default='0')

    working_hours = models.CharField(choices=WORKING_HOURS,
                                     max_length=2,
                                     default='FT')

    employment_patterns = models.CharField(choices=EMPLOYMENT_PATTERNS,
                                           max_length=3,
                                           default='FT')

    salary_min = models.FloatField()
    salary_max = models.FloatField()
    request_id = models.ForeignKey(Request,
                                   on_delete=models.PROTECT)
    image_link = models.URLField()

    posts = ArrayField(
        models.CharField(max_length=12, blank=True),
        size=10
    )
    position_id = models.ForeignKey(Position,
                                    on_delete=models.PROTECT),
    created_by = models.ForeignKey(User,
                                   on_delete=models.PROTECT)


class Publication(models.Model):
    vacancy_id = models.ForeignKey(Vacancy, on_delete=models.PROTECT)
    created = models.DateTimeField(auto_now_add=True)
    created_by = models.ForeignKey(User, on_delete=models.PROTECT)
    facebook = models.BooleanField()
    instagram = models.BooleanField()
    headhunter = models.BooleanField()
    diesel = models.BooleanField()
    jobkg = models.BooleanField()
