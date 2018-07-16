from django.db.models.signals import post_save
from django.dispatch import receiver

from apps.requests.models import Request
from apps.vacancies.models import Vacancy


@receiver(post_save, sender=Vacancy)
def vacancy_created(sender, **kwargs):
    if kwargs['created']:
        instance = Request.objects.get(pk=kwargs['instance'].request.id)
        instance.is_vacancy_created = True
        instance.save()
