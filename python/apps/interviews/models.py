from django.db import models

# Create your models here.


class Interview(models.Model):
    candidate = models.ForeignKey('Candidate', on_delete=models.PROTECT)
    date = models.DateTimeField()
    request = models.ForeignKey('Request', on_delete=models.PROTECT)
