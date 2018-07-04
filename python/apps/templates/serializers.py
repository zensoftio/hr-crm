from rest_framework import serializers

from apps.templates.models import Template, Attachment


class TemplateListSerializer(serializers.ModelSerializer):
    class Meta:
        model = Template
        fields = ('id', 'subject', 'type', 'content', 'created')


class AttachmentSerializer(serializers.ModelSerializer):
    class Meta:
        model = Attachment
        exclude = []


class TemplateCreateSerializer(serializers.ModelSerializer):
    class Meta:
        model = Template
        exclude = ['modified', 'created']


class TemplateDetailSerializer(serializers.ModelSerializer):
    attachments = AttachmentSerializer(many=True, read_only=True)

    class Meta:
        model = Template
        exclude = []
