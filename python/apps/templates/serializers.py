from rest_framework import serializers

from apps.templates.models import Template


class TemplateListSerialzer(serializers.ModelSerializer):

    class Meta:
        model = Template
        fields = ('id', 'subject', 'type', 'content', 'created')
