
from djcelery import celery
from apps.utils.rabbitmq import RabbitMQ
from apps.departments.models import Department

@celery.task
def send_message_to_java(queryset, serializer):
	rabbit = RabbitMQ(host="192.168.89.80", user="guest", password="guest")
	rabbit.call_java(queryset, serializer, exchange_name='share',
    			q_receiving='shareResponse', q_sending='facebook.publish')

