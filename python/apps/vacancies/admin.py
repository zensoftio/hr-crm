from django.contrib import admin

from apps.departments.models import Position, Department
from apps.requests.models import Request
from apps.vacancies.models import Vacancy

admin.site.register(Department)
admin.site.register(Position)
admin.site.register(Request)
admin.site.register(Vacancy)
