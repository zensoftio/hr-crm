from django.urls import path

from .views import InterviewListView, InterviewDestroy

urlpatterns = [
    path('interviews/', InterviewListView.as_view()),
    path('interviews/<int:pk>', InterviewDestroy.as_view()),
]
