from django.core.exceptions import ValidationError


def email_validator(email):
    if not str(email).endswith('zensoft.io'):
        raise ValidationError("Email should end with'zensoft.io' domain")
