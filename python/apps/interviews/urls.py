from django.urls import path
from .views import InterviewListView

urlpatterns = [
    path('interviews/', InterviewListView.as_view())
]
