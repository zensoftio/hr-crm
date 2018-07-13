from rest_framework import exceptions, generics

from apps.departments.models import Department, Position, Requirement
from apps.departments.serializers import DepartmentSerializer, PositionSerializer, RequirementSerializer, \
                                                                                            RequirementCreateSerializer


class MethodSerializerView(object):
    """
    Utility class for get different serializer class by method.
    For example:
    method_serializer_classes = {
        ('GET', ): MyModelListViewSerializer,
        ('PUT', 'PATCH'): MyModelCreateUpdateSerializer
    }
    """
    method_serializer_classes = None

    def get_serializer_class(self):
        assert self.method_serializer_classes is not None, (
            'Expected view %s should contain method_serializer_classes '
            'to get right serializer class.' %
            (self.__class__.__name__, )
        )
        for methods, serializer_cls in self.method_serializer_classes.items():
            if self.request.method in methods:
                return serializer_cls

        raise exceptions.MethodNotAllowed(self.request.method)


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
