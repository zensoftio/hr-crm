from django.urls import path

from apps.evaluations import views

urlpatterns = [
    path('criterias/', views.CriteriaCreateListView.as_view()),
]
