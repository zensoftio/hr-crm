from rest_framework import generics

from apps.utils.serializers import MethodSerializerView
from apps.departments.models import Department, Position, Requirement
from apps.departments.serializers import DepartmentSerializer, PositionListSerializer, RequirementSerializer, \
                                                        RequirementCreateSerializer, PositionCreateSerializer


class DepartmentCreateListView(generics.ListCreateAPIView):
    """ Return all departments and create department """
    queryset = Department.objects.all()
    serializer_class = DepartmentSerializer


class DepartmentRetrieve(generics.RetrieveAPIView):
    """ Return department by id """
    queryset = Department.objects.all()
    serializer_class = DepartmentSerializer


class PositionListCreateView(MethodSerializerView, generics.ListCreateAPIView):
    """ Return all positions and create position """
    queryset = Position.objects.all()
    filter_fields = ('department',)

    method_serializer_classes = {
        ('GET',): PositionListSerializer,
        ('POST',): PositionCreateSerializer
    }


class PositionRetrieve(generics.RetrieveAPIView):
    """ Return position by id """
    queryset = Position.objects.all()
    serializer_class = PositionListSerializer


class RequirementCreateView(MethodSerializerView, generics.ListCreateAPIView):
    """ Return list requirements and create requirement """
    queryset = Requirement.objects.all()
    filter_fields = ('type', 'department')

    method_serializer_classes = {
        ('GET',): RequirementSerializer,
        ('POST',): RequirementCreateSerializer
    }


class RequirementRetrieve(generics.RetrieveAPIView):
    """ Return requirement by id """
    queryset = Requirement.objects.all()
    serializer_class = RequirementSerializer
