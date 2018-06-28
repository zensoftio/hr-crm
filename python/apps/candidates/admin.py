from django.contrib import admin

from apps.candidates.models import Comment, CV, Candidate

admin.site.register([Candidate, CV, Comment])
