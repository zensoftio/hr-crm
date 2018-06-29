from django.contrib.auth import get_user_model
from django.db import models

from apps.departments.models import Position

User = get_user_model()


class Request(models.Model):
    position = models.ForeignKey(Position, on_delete=models.PROTECT)
    status = models.IntegerField(default='-1')
    count = models.IntegerField()
    created = models.DateTimeField(auto_now_add=True)
    created_by = models.ForeignKey(User, on_delete=models.PROTECT)
    modified = models.DateTimeField(auto_now=True)

    # requirements = models.ForeignKey(Requirement, on_delete=models.PROTECT)
    # requirements = models.IntegerField(default='-1111')

    def __str__(self):
        return self.position.name
