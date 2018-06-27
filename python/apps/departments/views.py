from rest_framework import generics
from apps.departments.models import Department
from apps.departments.serializers import DepartamentSerializer


class DepartmentList(generics.ListCreateAPIView):
    queryset = Department.objects.all()
    serializer_class = DepartamentSerializer
