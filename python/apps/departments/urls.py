from django.urls import path
from apps.departments import views


urlpatterns = [
    path('departments/', views.DepartmentCreateListView.as_view()),
    path('departments/<int:pk>', views.RequirementCreateView.as_view()),
]

