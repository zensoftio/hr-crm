from django.urls import path, re_path
from apps.departments import views


urlpatterns = [
    path('departments/', views.DepartmentCreateListView.as_view()),
    path('requirements/', views.RequirementListCreateView.as_view()),
    path('positions/', views.PositionListCreateView.as_view()),
]