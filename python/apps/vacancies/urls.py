from django.urls import path

from .views import VacancyListView, VacancyDetailView, PublicationList, PublicationDetail

urlpatterns = (
    path('vacancies', VacancyListView.as_view(), name='vacancy-list'),
    path('vacancies/<int:pk>', VacancyDetailView.as_view(), name='vacancy-detail'),
    path('publications', PublicationList.as_view(), name='publication-list'),
    path('publications/<int:pk>', PublicationDetail.as_view(), name='publication-detail')
)
