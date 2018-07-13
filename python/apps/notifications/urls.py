from django.urls import path

from apps.notifications.views import CreateDeviceView

urlpatterns = [
    path('devices', CreateDeviceView.as_view())
]
