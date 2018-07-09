from django.contrib.auth import get_user_model
from django.db import models
from django.urls import reverse
from django.db.models.signals import post_save

from apps.departments.models import Position, Requirement
from apps.utils.notifications import request_created

User = get_user_model()


class Request(models.Model):
    count = models.IntegerField()
    created = models.DateTimeField(auto_now_add=True)
    created_by = models.ForeignKey(User, on_delete=models.PROTECT)
    modified = models.DateTimeField(auto_now=True)
    position = models.ForeignKey(Position, on_delete=models.PROTECT)
    status = models.IntegerField()
    requirements = models.ManyToManyField(Requirement)

    def __str__(self):
        return self.position.name

    def get_absolute_url(self):
        return reverse('request-detail', kwargs={'pk': self.id})

    class Meta:
        default_related_name = 'requests'


post_save.connect(receiver=request_created, sender=Request)
