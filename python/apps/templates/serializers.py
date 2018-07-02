from rest_framework import serializers

from apps.templates.models import Template


class TemplateListSerializer(serializers.ModelSerializer):

    class Meta:
        model = Template
        fields = ('id', 'subject', 'type', 'content', 'created')
