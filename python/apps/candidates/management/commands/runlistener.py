from django.core.management import BaseCommand
from pika.exceptions import ConnectionClosed

from apps.candidates.listener import main


class Command(BaseCommand):
    help = 'Runs Listener for RabbitMQ queue which send new Candidates instances to create'

    def handle(self, *args, **options):
        try:
            main()
        except ConnectionClosed:
            self.handle(*args, **options)
