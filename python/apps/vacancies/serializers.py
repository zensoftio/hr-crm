from rest_framework import serializers

from django.contrib.auth.models import Vacancy


class SnippetSerializer(serializers.ModelSerializer):
	
	class Meta:
		model = Snippet
		fields = ('url', 'id', 'highlight', 'owner', 
				  'title', 'code', 'linenos', 'language',
				  'style')



class UserSerializer(serializers.HyperlinkedModelSerializer):
	snippets = serializers.HyperlinkedIdentityField(many=True, view_name='snippet-detail', read_only=True)

	class Meta:
		model = User
		fields = ('url','id', 'username', 'snippets')


