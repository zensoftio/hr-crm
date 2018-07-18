from django.urls import path

from .views import InterviewListCreateView, CriteriaCreateListView, InterviewDetailView, EvaluationCreateView

urlpatterns = [
    path('criterias', CriteriaCreateListView.as_view()),
    path('interviews', InterviewListCreateView.as_view()),
    path('interviews/<int:pk>', InterviewDetailView.as_view(), name='interview-detail'),
    path('evaluations', EvaluationCreateView.as_view())
]
