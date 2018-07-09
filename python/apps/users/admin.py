from django.contrib import admin
from django.contrib.auth import get_user_model
from django.contrib.auth.admin import UserAdmin
from django.contrib.auth.forms import UserChangeForm
from django.db.models import ManyToManyField
from django.utils.translation import gettext_lazy as _

from apps.users.models import User


class CustomUserChangeForm(UserChangeForm):
    departments = ManyToManyField(to='apps.departments.models.Department')

    class Meta(UserChangeForm.Meta):
        model = get_user_model()
        fields = '__all__'


class CustomUserAdmin(UserAdmin):
    form = CustomUserChangeForm
    list_display = ('email', 'first_name', 'last_name')
    ordering = ('email',)
    search_fields = ('first_name', 'last_name', 'email')

    fieldsets = (
        (None, {'fields': ('email', 'password', 'departments')}),
        (_('Personal info'), {'fields': ('first_name', 'last_name')}),
    )

    add_fieldsets = (
        (None, {
            'classes': ('wide',),
            'fields': ('email', 'password1', 'password2', 'departments'),
        }),
    )


admin.site.register(User, CustomUserAdmin)
