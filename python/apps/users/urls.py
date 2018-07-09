from django.urls import path, include

from .views import UserListView, UserDetailView

urlpatterns = [
    path('users/', UserListView.as_view()),
    path('users/<int:pk>/', UserDetailView.as_view(), name='user-detail'),
    path('auth/', include('rest_framework_social_oauth2.urls')),
]
