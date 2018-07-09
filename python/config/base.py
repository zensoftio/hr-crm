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
        'corsheaders',

        'social_django',
        'oauth2_provider',
        'rest_framework.authtoken',
        'rest_framework_social_oauth2',
        'fcm_django',

        'apps.candidates',
        'apps.departments',
        'apps.interviews',
        'apps.requests',
        'apps.users',
        'apps.vacancies',
        'apps.templates'
    ]

    SOCIAL_AUTH_RAISE_EXCEPTIONS = True
    SOCIAL_AUTH_URL_NAMESPACE = 'social'

    SOCIAL_AUTH_GOOGLE_OAUTH2_KEY = values.SecretValue()
    SOCIAL_AUTH_GOOGLE_OAUTH2_SECRET = values.SecretValue()

    CSRF_COOKIE_SECURE = False
    CORS_ORIGIN_ALLOW_ALL = True

    FCM_SERVER_KEY = values.SecretValue().to_python(os.environ.get('DJANGO_FCM_SERVER_KEY'))

    FCM_DJANGO_SETTINGS = {
        "FCM_SERVER_KEY": FCM_SERVER_KEY,
    }

    AUTHENTICATION_BACKENDS = (
        'social_core.backends.google.GoogleOAuth2',
        'social_core.backends.google.GooglePlusAuth',
        'rest_framework_social_oauth2.backends.DjangoOAuth2',
        'django.contrib.auth.backends.ModelBackend',)

    SOCIAL_AUTH_PIPELINE = (
        'social_core.pipeline.social_auth.social_details',
        'social_core.pipeline.social_auth.social_uid',
        'social_core.pipeline.social_auth.auth_allowed',
        'social_core.pipeline.social_auth.social_user',
        'social_core.pipeline.user.get_username',
        'social_core.pipeline.social_auth.associate_by_email',
        'social_core.pipeline.social_auth.associate_user',
        'social_core.pipeline.social_auth.load_extra_data',
        'social_core.pipeline.user.user_details',
    )

    MIDDLEWARE = [
        'corsheaders.middleware.CorsMiddleware',
        'django.middleware.security.SecurityMiddleware',
        'django.contrib.sessions.middleware.SessionMiddleware',
        'django.middleware.common.CommonMiddleware',
        'django.middleware.csrf.CsrfViewMiddleware',
        'django.contrib.auth.middleware.AuthenticationMiddleware',
        'django.contrib.messages.middleware.MessageMiddleware',
        'django.middleware.clickjacking.XFrameOptionsMiddleware',
        'corsheaders.middleware.CorsMiddleware',
        'django.middleware.common.CommonMiddleware',
    ]

    CORS_ORIGIN_WHITELIST = values.ListValue()

    CORS_ALLOW_METHODS = (
        'DELETE',
        'GET',
        'OPTIONS',
        'PATCH',
        'POST',
        'PUT',
    )

    CORS_ALLOW_HEADERS = (
        'accept',
        'accept-encoding',
        'authorization',
        'content-type',
        'dnt',
        'origin',
        'user-agent',
        'x-csrftoken',
        'x-requested-with',
    )

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
                    'social_django.context_processors.backends',
                    'social_django.context_processors.login_redirect',
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
        'DEFAULT_PAGINATION_CLASS': 'apps.utils.pagination.SizedPageNumberPagination',
        'PAGE_SIZE': 10,
        'DEFAULT_VERSIONING_CLASS': 'rest_framework.versioning.NamespaceVersioning',
        'DEFAULT_PERMISSION_CLASSES': [
            'rest_framework.permissions.AllowAny',
        ],
        'DEFAULT_FILTER_BACKENDS': ('rest_framework.filters.SearchFilter',
                                    'django_filters.rest_framework.DjangoFilterBackend',
                                    'rest_framework.filters.OrderingFilter',),
        'DEFAULT_AUTHENTICATION_CLASSES': (
            'oauth2_provider.contrib.rest_framework.OAuth2Authentication',
            'rest_framework_social_oauth2.authentication.SocialAuthentication',
        ),
    }

    LANGUAGE_CODE = 'en-us'

    TIME_ZONE = 'Asia/Bishkek'

    USE_I18N = True

    USE_L10N = True

    USE_TZ = True
