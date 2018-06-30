from django.contrib import admin
from apps.evaluations.models import Criteria, Evaluation, EvaluationCriteriaRelationship


admin.site.register([Evaluation, Criteria, EvaluationCriteriaRelationship])
