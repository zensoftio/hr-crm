from django.contrib import admin
from apps.departments.models import Position, Requirement, Department


admin.site.register(Requirement)
admin.site.register(Department)
admin.site.register(Position)
