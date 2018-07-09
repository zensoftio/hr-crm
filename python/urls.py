from django.contrib import admin
from django.urls import path, include

v1 = ([
          path('', include('apps.candidates.urls')),
          path('', include('apps.departments.urls')),
          path('', include('apps.interviews.urls')),
          path('', include('apps.requests.urls')),
          path('', include('apps.users.urls')),
          path('', include('apps.vacancies.urls')),
          path('', include('apps.templates.urls')),
      ], 'v1')

urlpatterns = [
    path('admin/', admin.site.urls),
    path('auth/', include('rest_framework_social_oauth2.urls')),
    path('api/v1/', include(v1)),
]
