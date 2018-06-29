from django.urls import path

from apps.evaluations import views

urlpatterns = [
    path('evaluations/', views.CriteriaListView.as_view()),
    path('criteria/', views.CriteriaListView.as_view()),
]
