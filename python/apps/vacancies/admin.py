from django.contrib import admin

from apps.departments.models import Position, Department
from apps.requests.models import Request
from apps.vacancies.models import Vacancy

# Register your models here.

admin.site.register(Vacancy)
