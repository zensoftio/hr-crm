from django.urls import path

from apps.authentication.views import AndroidAuthenticationView

urlpatterns = [
    path('auth/android/', AndroidAuthenticationView.as_view()),
]
