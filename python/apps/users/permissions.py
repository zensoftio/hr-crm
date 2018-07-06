from rest_framework.authtoken.models import Token
from rest_framework.permissions import BasePermission


class IsAuthenticatedByGoogle(BasePermission):

    def has_permission(self, request, view):
        token = Token.objects.all()
        pass
