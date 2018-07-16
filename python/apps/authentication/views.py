import json

import requests as req
from django.conf import settings
from django.contrib.auth import get_user_model
from django.core.exceptions import ObjectDoesNotExist
from google.auth.transport import requests
from google.oauth2 import id_token
from oauth2_provider.models import get_access_token_model
from rest_framework import status as s
from rest_framework.response import Response
from rest_framework.views import APIView
from rest_framework_social_oauth2.views import ConvertTokenView

User = get_user_model()
AccessToken = get_access_token_model()


class AndroidAuthenticationView(APIView):
    CLIENT_ID = settings.SOCIAL_AUTH_GOOGLE_OAUTH2_KEY

    def get(self, request, *args, **kwargs):
        try:
            ID_token = self.request.query_params.get('id_token')
            id_token_info = id_token.verify_oauth2_token(ID_token, requests.Request(), self.CLIENT_ID)

            if id_token_info['iss'] not in ['accounts.google.com', 'https://accounts.google.com']:
                raise ValueError

            user_email = id_token_info['email']

            try:
                user = User.objects.get(email=user_email)
                token = AccessToken.objects.filter(user=user).last()
                return Response(data={'access_token': token.token})

            except ObjectDoesNotExist:
                return Response(data={'error': 'User does not exist'}, status=s.HTTP_404_NOT_FOUND)

        except ValueError:
            return Response(data={'error': 'Token is invalid'}, status=s.HTTP_400_BAD_REQUEST)


class ConvertRoleTokenView(ConvertTokenView):
    def post(self, request, *args, **kwargs):
        request._request.POST = request._request.POST.copy()
        for key, value in request.data.items():
            request._request.POST[key] = value

        token_to_convert = request.data.get('token')
        validation_url = 'https://www.googleapis.com/oauth2/v1/tokeninfo?access_token=' + token_to_convert
        response = req.get(validation_url).json()

        try:
            email = response.get('email', None)
            if email:
                user = User.objects.get(email=email)
            else:
                return Response({'error': 'invalid_access_token'})
            roles_scope = user.groups.first()

        except ObjectDoesNotExist:
            return Response(
                data={'error': 'User with such email does not exist'},
                status=s.HTTP_404_NOT_FOUND
            )

        url, headers, body, status = self.create_token_response(request._request)
        headers['role'] = roles_scope
        response = Response(data=json.loads(body), status=status)

        for k, v in headers.items():
            response[k] = v

        return response
