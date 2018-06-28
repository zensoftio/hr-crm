from django.urls import path

from apps.candidates import views

urlpatterns = [
    path('candidates/', views.CandidateList.as_view()),
]
