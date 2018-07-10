import uuid

from django.contrib.auth import get_user_model
from django.contrib.postgres.fields import ArrayField
from django.db import models
from django.urls import reverse

from apps.requests.models import Request

User = get_user_model()

WORKING_HOURS = (("FULL_TIME", 'Полный рабочий день'),
                 ("PART_TIME", 'Гибкий график'),
                 ("REMOTE_JOB", 'Удаленная работа'),
                 )


class Vacancy(models.Model):
    uuid = models.UUIDField(default=uuid.uuid4, editable=False, unique=True)
    title = models.CharField(max_length=200)
    request = models.ForeignKey(Request, on_delete=models.PROTECT)
    created_by = models.ForeignKey(User, on_delete=models.PROTECT)
    city = models.CharField(max_length=30)
    address = models.CharField(max_length=50)
    work_conditions = ArrayField(base_field=models.CharField(max_length=200, blank=True))
    working_hours = models.CharField(choices=WORKING_HOURS, max_length=10, default="FULL_TIME")

    salary_min = models.FloatField()
    salary_max = models.FloatField()
    image = models.ImageField(upload_to='media', null=True)
    responsibilities = models.TextField(null=True)
    comments = models.TextField(null=True)
    created = models.DateTimeField(auto_now_add=True)
    last_published = models.DateTimeField(auto_now=True)

    class Meta:
        default_related_name = 'vacancies'
        verbose_name_plural = 'vacancies'

    def get_absolute_url(self):
        return reverse('v1:vacancy-detail', kwargs={'pk': self.id})


class Publication(models.Model):
    vacancy = models.ForeignKey(Vacancy, on_delete=models.PROTECT)
    created_by = models.ForeignKey(User, on_delete=models.PROTECT)
    created = models.DateTimeField(auto_now_add=True)
    facebook = models.BooleanField(default=False)
    facebook_url = models.URLField(null=True)
    diesel_exist = models.BooleanField(default=False)
    diesel_url = models.URLField(default=True)
    jobkg_exist = models.BooleanField(default=False)
    jobkg_url = models.URLField(null=True)

    class Meta:
        default_related_name = 'publications'

    def get_absolute_url(self):
        return reverse('v1:publication-detail', kwargs={'pk': self.id})
