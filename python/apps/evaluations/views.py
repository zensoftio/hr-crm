from rest_framework import generics
from apps.evaluations.models import Evaluation, Criteria
from apps.evaluations.serializers import EvaluationSerializer, CriteriaSerializer


class CriteriaListView(generics.ListAPIView):
    queryset = Criteria
    serializer_class = CriteriaSerializer

