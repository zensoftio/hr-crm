from rest_framework import generics
from apps.departments.models import Department, Position
from apps.departments.serializers import DepartmentSerializer, PositionSerializer


class DepartmentCreateListView(generics.ListCreateAPIView):
    queryset = Department.objects.all()
    serializer_class = DepartamentSerializer
