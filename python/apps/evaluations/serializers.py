from rest_framework import serializers
from apps.evaluations.models import Criteria, Evaluation


class EvaluationSerializer(serializers.ModelSerializer):

    class Meta:
        model = Evaluation
        fields = ('comments', 'interview', 'reviewer')


class CriteriaSerializer(serializers.ModelSerializer):

    class Meta:
        model = Criteria
        fields = ('name', 'department')
