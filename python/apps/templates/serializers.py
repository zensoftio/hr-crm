import os

from rest_framework import serializers

from apps.templates.models import Template, Attachment


class TemplateListSerializer(serializers.ModelSerializer):
    class Meta:
        model = Template
        fields = ('id', 'subject', 'type', 'content', 'created')


class Base64FileField(serializers.FileField):
    """
    A Django REST framework field for handling file-uploads through raw post data.
    It uses base64 for encoding and decoding the contents of the file.
    """

    def to_internal_value(self, data):
        from django.core.files.base import ContentFile
        import base64
        import six
        import uuid

        # Check if this is a base64 string
        if isinstance(data, six.string_types):
            # Check if the base64 string is in the "data:" format
            if 'data:' in data and ';base64,' in data:
                # Break out the header from the base64 content
                header, data = data.split(';base64,')

            try:
                decoded_file = base64.b64decode(data)
            except TypeError:
                self.fail('invalid_file')

            # Generate file name:
            file_name = str(uuid.uuid4())[:12]
            file_extension = os.path.splitext(file_name)[1]

            complete_file_name = "%s.%s" % (file_name, file_extension,)

            data = ContentFile(decoded_file, name=complete_file_name)

        return super(Base64FileField, self).to_internal_value(data)


class AttachmentSerializer(serializers.ModelSerializer):
    file = Base64FileField(
        max_length=None, use_url=True,
    )

    class Meta:
        model = Attachment
        exclude = []


class TemplateCreateSerializer(serializers.ModelSerializer):
    attachments = AttachmentSerializer(many=True)

    class Meta:
        model = Template
        exclude = ['modified', 'created']

    def create(self, validated_data):
        attachments = validated_data.pop('attachments')

        template = Template.objects.create(**validated_data)

        for attach in attachments:
            file = attach['file']
            type = attach['type']
            attachment = Attachment.objects.create(file=file, type=type)
            template.attachments.add(attachment)
            template.save()
        return template


class TemplateDetailSerializer(serializers.ModelSerializer):
    attachments = AttachmentSerializer(many=True, read_only=True)

    class Meta:
        model = Template
        exclude = []
