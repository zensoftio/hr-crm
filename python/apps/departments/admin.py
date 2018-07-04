from django.contrib import admin
from apps.departments.models import Position, Requirement, Department

admin.site.register([Department, Requirement, Position])

