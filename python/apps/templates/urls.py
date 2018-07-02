from django.urls import path

from apps.templates import views

urlpatterns = [
    path('templates/', views.TemplateListCreateView.as_view())
]
