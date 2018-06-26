from configurations import values

from .base import Base


class Production(Base):
    """Configuration for production"""
    SECRET_KEY = values.SecretValue()
    DEBUG = values.BooleanValue(True)

    INSTALLED_APPS = Base.INSTALLED_APPS
    INSTALLED_APPS += ('gunicorn',)
