from django.urls import path

from apps.requests import views

urlpatterns = [
    path('requests/', views.RequestListCreateView.as_view()),
    path('requests/<int:pk>', views.RequestDetail.as_view(), name='request-detail')
]
