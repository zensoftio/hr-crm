import json

import pika

from apps.candidates.models import Candidate, CV
from config.base import Base


def callback(ch, method, properties, messages):
    messages = json.loads(messages.decode('utf-8'))
    if messages:
        for message in messages:
            email = message.get('email')
            candidate, created = Candidate.objects.get_or_create(email=email)

            attachments = message.get('url')
            if attachments:
                for attachment in attachments:
                    cv, created = CV.objects.get_or_create(url=attachment, candidate=candidate)

            print('Candidate: ', candidate.email)


def main():
    parameters = pika.URLParameters(
        'amqp://{login}:{password}@{host}:{port}'.format(
            login=Base.RABBITMQ_USERNAME,
            password=Base.RABBITMQ_PASSWORD,
            host=Base.RABBITMQ_HOST,
            port=Base.RABBITMQ_PORT,
        )
    )

    connection = pika.BlockingConnection(parameters)
    channel = connection.channel()
    channel.exchange_declare(exchange='candidates', exchange_type='direct')
    channel.queue_declare(queue='candidate_queue', durable=False)

    channel.queue_bind(exchange='candidates', queue='candidate_queue', routing_key='candidate')

    print(' [*] Waiting for Candidates. To exit press CTRL+C')
    channel.basic_consume(callback, queue='candidate_queue', no_ack=True)
    channel.start_consuming()
