from .views import VacancyList, VacancyDetail, PublicationList, PublicationDetail
from django.urls import path


urlpatterns = (
    path('vacancies/', VacancyList.as_view(), name='vacancy-list'),
    path('vacancies/<int:pk>', VacancyDetail.as_view(), name='vacancy-detail'),
    path('publications/', PublicationList.as_view(), name='publication-list'),
    path('publications/<int:pk>', PublicationDetail.as_view(), name='publication-detail')
)
