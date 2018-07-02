from django.contrib.auth import get_user_model
from django.db import models

User = get_user_model()


class Template(models.Model):
    subject = models.CharField(max_length=1000)
    type = models.CharField(max_length=10)
    content = models.TextField()
    created = models.DateTimeField(auto_now_add=True)
    modified = models.DateTimeField(auto_now=True)

    def __str__(self):
        return self.subject

    class Meta:
        default_related_name = 'templates'


class Attachment(models.Model):
    file = models.FileField()
    type = models.CharField(max_length=10)
    created = models.DateTimeField(auto_now_add=True)

    def __str__(self):
        return self.type

    class Meta:
        default_related_name = 'attachments'
