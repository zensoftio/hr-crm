from django.contrib import admin
from django.urls import path, include

urlpatterns = [
    path('admin/', admin.site.urls),
    path('candidates/', include('apps.candidates.urls')),
    path('departments/', include('apps.departments.urls')),
    path('evaluations/', include('apps.evaluations.urls')),
    path('interviews/', include('apps.interviews.urls')),
    path('requests/', include('apps.requests.urls')),
    path('users/', include('apps.users.urls')),
    path('vacancies/', include('apps.vacancies.urls')),
]
