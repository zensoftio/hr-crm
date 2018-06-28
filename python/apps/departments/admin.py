from django.contrib import admin

# Register your models here.
from apps.departments.models import Position, Department, Requirement

admin.site.register([Department, Requirement, Position])
