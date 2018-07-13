from django.core.management import BaseCommand

from apps.candidates.listener import main


class Command(BaseCommand):
    help = 'Runs Listener for RabbitMQ queue which send new Candidates instances to create'

    def handle(self, *args, **options):
        main()
