from django.urls import path

from apps.candidates.views import test_func

urlpatterns = [
    path('evaluations/', test_func)
]
