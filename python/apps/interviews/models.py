from django.db import models

# Create your models here.
from apps.candidates.models import Candidate
from apps.requests.models import Request


class Interview(models.Model):
    candidate = models.ForeignKey(Candidate, on_delete=models.PROTECT)
    date = models.DateTimeField()
    request = models.ForeignKey(Request, on_delete=models.PROTECT)
