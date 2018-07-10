from fcm_django.models import FCMDevice
from rest_framework import serializers


class DeviceSerializer(serializers.ModelSerializer):
    class Meta:
        model = FCMDevice
        fields = ('name', 'registration_id', 'type')

