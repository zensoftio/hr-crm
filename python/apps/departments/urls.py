from django.urls import path
from apps.departments import views


urlpatterns = [
    path('', views.DepartmentList.as_view()),
]

