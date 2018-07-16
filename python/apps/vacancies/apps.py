from django.apps import AppConfig


class VacanciesConfig(AppConfig):
    name = 'apps.vacancies'

    def ready(self):
        import apps.vacancies.signals
