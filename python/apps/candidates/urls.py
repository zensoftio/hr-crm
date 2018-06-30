from django.urls import path

from apps.candidates import views

urlpatterns = [
    path('candidates/', views.CandidateListView.as_view()),
    path('candidates/<int:pk>', views.CandidateDetailView.as_view()),
    path('comments/', views.CommentCreateView.as_view()),
]
