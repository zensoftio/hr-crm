from rest_framework import permissions


class IsHeadOfDepartment(permissions.BasePermission):
    """
    Permission for HoD:
        - CRUD of 'Requests'

    """

    def has_permission(self, request, view):
        return request.user.groups.filter(name='Head of Department').exists()


class IsHR(permissions.BasePermission):
    """
    Permission for HR:
        - CRUD of all entities (except Requests!!!)
        - ...
    """

    def has_permission(self, request, view):
        return request.user.groups.filter(name='HR').exists()


class IsPM(permissions.BasePermission):
    """
    Permission for HR:
        - Decline or approve Requests aka Update Request
        - ...
    """

    def has_permission(self, request, view):
        return request.user.groups.filter(name='PM').exists()


class IsInterviewer(permissions.BasePermission):
    """
    Permission for Interviewer:
        - Watch and/or update Interviews
        - ...
    """

    def has_permission(self, request, view):
        return request.user.groups.filter(name='Interviewer').exists()
