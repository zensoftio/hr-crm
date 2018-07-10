from fcm_django.models import FCMDevice
from rest_framework import status
from rest_framework.generics import CreateAPIView
from rest_framework.permissions import IsAuthenticated
from rest_framework.response import Response

from apps.notifications.serializers import DeviceSerializer


class CreateDeviceView(CreateAPIView):
    serializer_class = DeviceSerializer
    queryset = FCMDevice.objects.all()
    permission_classes = (IsAuthenticated,)

    def create(self, request, *args, **kwargs):
        device = FCMDevice(**request.data)
        user = request.user
        device.user = self.request.user
        device.save()

        return Response(DeviceSerializer(device).data, status=status.HTTP_201_CREATED)
