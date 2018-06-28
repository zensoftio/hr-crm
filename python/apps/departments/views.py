from rest_framework import generics
from apps.departments.models import Department, Position, Requirement
from apps.departments.serializers import DepartmentSerializer, PositionSerializer, RequirementSerializer


class DepartmentCreateListView(generics.ListCreateAPIView):
    queryset = Department.objects.all()
    serializer_class = DepartmentSerializer


class RequirementCreateView(generics.CreateAPIView):
    queryset = Requirement.objects.all()
    serializer_class = RequirementSerializer()
