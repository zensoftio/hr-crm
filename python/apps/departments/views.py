from rest_framework import generics

from apps.departments.models import Department, Position, Requirement
from apps.departments.serializers import DepartmentSerializer, PositionSerializer, RequirementSerializer


class DepartmentCreateListView(generics.ListCreateAPIView):
    """ Return all departments and create department """
    queryset = Department.objects.all()
    serializer_class = DepartmentSerializer


class DepartmentRetrieve(generics.RetrieveAPIView):
    """ Return department by id """
    queryset = Department.objects.all()
    serializer_class = DepartmentSerializer


class PositionListCreateView(generics.ListCreateAPIView):
    """ Return all positions and create position """
    queryset = Position.objects.all()
    serializer_class = PositionSerializer
    filter_fields = ('department',)


class PositionRetrieve(generics.RetrieveAPIView):
    """ Return position by id """
    queryset = Position.objects.all()
    serializer_class = PositionSerializer


class RequirementCreateView(generics.ListCreateAPIView):
    """ Return list requirements and create requirement """
    queryset = Requirement.objects.all()
    serializer_class = RequirementSerializer
    filter_fields = ('type', 'department')


class RequirementRetrieve(generics.RetrieveAPIView):
    """ Return requirement by id """
    queryset = Requirement.objects.all()
    serializer_class = RequirementSerializer
