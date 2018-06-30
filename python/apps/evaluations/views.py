from rest_framework import generics
from apps.evaluations.models import Criteria
from apps.evaluations.serializers import CriteriaSerializer


class CriteriaCreateListView(generics.ListCreateAPIView):
    queryset = Criteria.objects.all()
    serializer_class = CriteriaSerializer






