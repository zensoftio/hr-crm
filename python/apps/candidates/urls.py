from django.urls import path
from apps.candidates import views

urlpatterns = [
    path('', views.CandidateList.as_view()),
]
