from apps.requests.models import Request


def vacancy_created(sender, **kwargs):
    if kwargs['created']:
        instance = Request.objects.get(pk=kwargs['instance'].request.id)
        instance.is_vacancy_created = True
        instance.save()

