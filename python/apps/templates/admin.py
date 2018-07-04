from django.contrib import admin

from apps.templates.models import Template, Attachment

admin.site.register([Template, Attachment])
