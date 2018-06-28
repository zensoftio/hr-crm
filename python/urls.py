from django.contrib import admin
from django.urls import path, include

urlpatterns = [
    path('admin/', admin.site.urls),
    path('', include('apps.candidates.urls')),
    path('', include('apps.departments.urls')),
    path('', include('apps.evaluations.urls')),
    path('', include('apps.interviews.urls')),
    path('', include('apps.requests.urls')),
    path('', include('apps.users.urls')),
    path('', include('apps.vacancies.urls')),
]
