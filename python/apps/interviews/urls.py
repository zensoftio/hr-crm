from django.urls import path

from .views import InterviewListView, CriteriaCreateListView, InterviewDetailView

urlpatterns = [
    path('criterias/', CriteriaCreateListView.as_view()),
    path('interviews/', InterviewListView.as_view()),
    path('interviews/<int:pk>', InterviewDetailView.as_view())
]
