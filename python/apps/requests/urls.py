from django.urls import path
from apps.requests import views


urlpatterns = [
    path('', views.RequestList.as_view())
]
