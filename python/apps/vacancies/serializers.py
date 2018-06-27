from rest_framework import serializers

from .models import Vacancy, Publication


# Serializers define the API representation.
class VacancySerializer(serializers.ModelSerializer):
    class Meta:
        model = Vacancy
        fields = ('id', 'created', 'modified', 'topic', 'city', 'address',
                  'hh_payment_type', 'work_conditions', 'experience', 'working_hours',
                  'employment_patterns', 'salary_min', 'salary_min', 'salary_max',
                  'request_id', 'image_link', 'posts', 'position_id', 'created_by')


class PublicationSerializer(serializers.ModelSerializer):
    class Meta:
        model = Publication
        fields = ('vacancy_id', 'created', 'created_by', 'facebook',
                  'instagram', 'headhunter', 'diesel', 'jobkg')
