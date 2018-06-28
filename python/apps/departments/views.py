from rest_framework import generics
from apps.departments.models import Department, Position, Requirement
from apps.departments.serializers import DepartmentSerializer, PositionSerializer, RequirementSerializer


class DepartmentCreateListView(generics.ListCreateAPIView):
    queryset = Department.objects.all()
    serializer_class = DepartmentSerializer


class RequirementListView(generics.ListAPIView):
    queryset = Requirement.objects.all()
    serializer_class = RequirementSerializer

    def get_queryset(self):
        pk = self.request.query_params.get('department')
        queryset = Requirement.objects.filter(department=pk)
        return queryset


class RequirementCreateView(generics.CreateAPIView):
    queryset = Requirement.objects.all()
    serializer_class = RequirementSerializer
