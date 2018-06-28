from .views import VacancyList, VacancyDetail, PublicationList, PublicationDetail

from django.urls import path

urlpatterns = (
    path('', VacancyList.as_view(), name='vacancy-list'),
    path('<int:pk>', VacancyDetail.as_view(), name='vacancy-detail'),
    path('', PublicationList.as_view(), name='publication-list'),
    path('<int:pk>', PublicationDetail.as_view(), name='publication-detail')
)


