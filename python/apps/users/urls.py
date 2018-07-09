from django.urls import path, include

from .views import UserListView, UserDetailView

urlpatterns = [
    path('users/', UserListView.as_view()),
    path('users/<int:pk>/', UserDetailView.as_view()),
    path('auth/', include('rest_framework_social_oauth2.urls')),
]
