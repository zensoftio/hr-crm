from django.urls import path, re_path
from apps.departments import views


urlpatterns = [
    path('departments/', views.DepartmentCreateListView.as_view()),
    path('requirements/', views.RequirementCreateView.as_view()),
    re_path('requirements/(?P<department>)', views.RequirementListView.as_view()),
]

