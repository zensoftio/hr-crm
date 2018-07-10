from django.urls import path
from django.conf import settings
from django.conf.urls.static import static

from apps.templates import views

urlpatterns = [
    path('templates/', views.TemplateListCreateView.as_view()),
    path('attachments/', views.AttachmentListCreateView.as_view()),
]

if settings.DEBUG:
    urlpatterns += static(settings.MEDIA_URL, document_root=settings.MEDIA_ROOT)
