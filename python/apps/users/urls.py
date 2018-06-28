from django.urls import path
from .views import UserListView, UserDetailView

urlpatterns = [
    path('', UserListView.as_view()),
    path('<int:pk>/', UserDetailView.as_view()),

]
