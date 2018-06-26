import os
from .base import Base
from configurations import values

BASE_DIR = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))


class Local(Base):
    """Configuration for local development"""
    DEBUG = values.BooleanValue(True)

    for config in Base.TEMPLATES:
        config['OPTIONS']['debug'] = DEBUG

