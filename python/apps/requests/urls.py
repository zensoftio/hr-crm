from django.urls import path
from apps.requests import views


urlpatterns = [
    path('requests/', views.RequestListCreateView.as_view())
]
