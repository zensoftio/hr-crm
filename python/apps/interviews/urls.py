from django.urls import path

from .views import InterviewListView, CriteriaCreateListView

urlpatterns = [
    path('criterias/', CriteriaCreateListView.as_view()),
    path('interviews/', InterviewListView.as_view())
]
