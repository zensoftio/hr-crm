from django.contrib import admin

from apps.interviews.models import Interview, Criteria, Evaluation, Interviewer

admin.site.register([Criteria, Evaluation, Interview, Interviewer])
