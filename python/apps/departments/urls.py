from django.urls import path, re_path
from apps.departments import views


urlpatterns = [
    path('departments/', views.DepartmentCreateListView.as_view()),
    path('departments/<int:pk>', views.DepartmentRetrieve.as_view(), name='department-detail'),
    path('positions/', views.PositionListCreateView.as_view()),
    path('positions/<int:pk>', views.PositionRetrieve.as_view(), name='position-detail'),
    path('requirements/', views.RequirementCreateView.as_view()),
    path('requirements/<int:pk>', views.RequirementRetrieve.as_view(), name='requirement-detail'),
]
