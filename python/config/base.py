import os
from configurations import Configuration, values


BASE_DIR = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))


class Base(Configuration):
    """Base configuration"""
    SECRET_KEY = 'Not a secret =)'

    DEBUG = values.BooleanValue(False)

    ALLOWED_HOSTS = values.ListValue()

    INSTALLED_APPS = [
        'django.contrib.admin',
        'django.contrib.auth',
        'django.contrib.contenttypes',
        'django.contrib.sessions',
        'django.contrib.messages',
        'django.contrib.staticfiles',

        'django_extensions',
        'rest_framework',
        'django_filters',

        'apps.candidates',
        'apps.departments',
        'apps.interviews',
        'apps.requests',
        'apps.users',
        'apps.vacancies',
        'apps.templates'
    ]

    MIDDLEWARE = [
        'django.middleware.security.SecurityMiddleware',
        'django.contrib.sessions.middleware.SessionMiddleware',
        'django.middleware.common.CommonMiddleware',
        'django.middleware.csrf.CsrfViewMiddleware',
        'django.contrib.auth.middleware.AuthenticationMiddleware',
        'django.contrib.messages.middleware.MessageMiddleware',
        'django.middleware.clickjacking.XFrameOptionsMiddleware',
    ]

    ROOT_URLCONF = 'urls'

    STATIC_URL = '/static/'
    STATIC_ROOT = os.path.join(BASE_DIR, 'static')
    MEDIA_URL = '/media/'
    MEDIA_ROOT = os.path.join(BASE_DIR, 'media')

    TEMPLATES = [
        {
            'BACKEND': 'django.template.backends.django.DjangoTemplates',
            'DIRS': STATIC_ROOT,
            'APP_DIRS': True,
            'OPTIONS': {
                'context_processors': [
                    'django.template.context_processors.debug',
                    'django.template.context_processors.request',
                    'django.contrib.auth.context_processors.auth',
                    'django.contrib.messages.context_processors.messages',
                ],
            },
        },
    ]

    for config in TEMPLATES:
        config['OPTIONS']['debug'] = DEBUG

    FIXTURE_DIRS = (
        'apps/fixtures',
    )

    WSGI_APPLICATION = 'wsgi.application'

    DATABASES = values.DatabaseURLValue('postgres://zensoftuser:zensoftpassword@localhost:5432/zensoftdb')
    AUTH_USER_MODEL = 'users.User'

    AUTH_PASSWORD_VALIDATORS = [
        {
            'NAME': 'django.contrib.auth.password_validation.UserAttributeSimilarityValidator',
        },
        {
            'NAME': 'django.contrib.auth.password_validation.MinimumLengthValidator',
        },
        {
            'NAME': 'django.contrib.auth.password_validation.CommonPasswordValidator',
        },
        {
            'NAME': 'django.contrib.auth.password_validation.NumericPasswordValidator',
        },
    ]

    REST_FRAMEWORK = {
        'DEFAULT_PAGINATION_CLASS': 'config.pagination.SizedPageNumberPagination',
        'PAGE_SIZE': 10,
        'DEFAULT_PERMISSION_CLASSES': [
            'rest_framework.permissions.AllowAny',
        ],
        'DEFAULT_FILTER_BACKENDS': ('rest_framework.filters.SearchFilter',
                                    'django_filters.rest_framework.DjangoFilterBackend',
                                    'rest_framework.filters.OrderingFilter',)
    }

    LANGUAGE_CODE = 'en-us'

    TIME_ZONE = 'Asia/Bishkek'

    USE_I18N = True

    USE_L10N = True

    USE_TZ = True
