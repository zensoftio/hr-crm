from django.urls import path

from apps.evaluations import views

urlpatterns = [
    path('evaluations/', views.CriteriaListView.as_view()),
    path('criterias/', views.CriteriaListView.as_view()),
]
