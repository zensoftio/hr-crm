from django.urls import path, include

from .views import UserListView, UserDetailView, check_token

urlpatterns = [
    path('users/', UserListView.as_view()),
    path('users/<int:pk>/', UserDetailView.as_view()),
    path('auth/', include('rest_framework_social_oauth2.urls')),
]

urlpatterns += [
    path('api/login/', include('rest_social_auth.urls_jwt')),
    path('api/login/', include('rest_social_auth.urls_token')),
    path('api/login/', include('rest_social_auth.urls_session')),
    path('api/check/', check_token),

]
