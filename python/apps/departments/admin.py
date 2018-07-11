from django.contrib import admin, messages

from apps.departments.models import Position, Requirement, Department


class DepartmentModelAdmin(admin.ModelAdmin):

    def has_change_permission(self, request, obj=None):
        if obj and obj.protected:
            messages.add_message(request, messages.WARNING, 'You cannot change this department')
            return False
        return super().has_change_permission(request, obj=obj)


admin.site.register(Department, DepartmentModelAdmin)
admin.site.register(Requirement)
admin.site.register(Position)
