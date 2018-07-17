import json

from django.conf import settings
from djcelery import celery

from apps.users.models import User
from apps.utils.rabbitmq import RabbitMQ
from apps.vacancies.models import Vacancy, Publication


@celery.task
def send_message_to_java(queryset, serializer, service='', facebook=False):

    rabbit = RabbitMQ(host=settings.RABBITMQ_HOST, user=settings.RABBITMQ_USERNAME, password=settings.RABBITMQ_PASSWORD)
    content = rabbit.call_java(queryset, serializer,
                               exchange_name='share',
                               q_receiving='shareResponse',
                               q_sending=service+'.publish', facebook=facebook)

    content = content.decode('utf-8')

    content = json.loads(content)

    vacancy = Vacancy.objects.get(uuid=content['uuid'])
    user = User.objects.get(email=vacancy.created_by)
    print(content)
    data = {"vacancy": vacancy,
            "created_by": user,
            "message": content['message'],
            "url": content['url'],
            "status": content['status'],
            "publisher_service": content['publisher_service_type']
            }
    print(data)

    Publication.objects.create(**data)
