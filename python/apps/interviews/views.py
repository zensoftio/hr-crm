from django.contrib.auth import get_user_model
from rest_framework import generics, status
from rest_framework.response import Response
import json

from apps.interviews.models import Interview, Criteria, Candidate, Interviewer, User
from apps.interviews.serializers import InterviewListSerializer, CriteriaSerializer, InterviewDetailSerializer, \
    InterviewCreateSerializer
from apps.utils.rabbitmq import RabbitMQ

User = get_user_model()


class InterviewListCreateView(generics.ListCreateAPIView):
    queryset = Interview.objects.all()
    serializer_class = InterviewListSerializer
    filter_fields = ('status', 'candidate__position')

    def update(self, request, *args, **kwargs):
        data = request.data

    def create(self, request, *args, **kwargs):
        data = request.data
        interviewers = User.objects.filter(pk__in=data['interviewers'])
        candidate = Candidate.objects.get(pk=data['candidate'])

        emails = []
        for interviewer in interviewers:
            emails.append(interviewer.email)

        emails.append(candidate.email)
        print(emails)

        # json data for send on microservice
        json_to_microservice = {
            "title": "create",
            "body": {
                "id_event": "",
                "begin_time": data['begin_time'],
                "end_time": data['begin_time'],
                "email": emails,
                "phone": data['phone'],
                "location": data['location'],
                "description": data['description']
            }
        }

        json_to_microservice = json.dumps(json_to_microservice)

        print(json_to_microservice)

        rabbitmq = RabbitMQ(host='localhost', user='local', password='local')
        rabbitmq.call(exchange_name='js-backend', exchange_type='direct', queue_to_send='event',
                      routing_key_to_send='event', queue_to_receive='event-response', message=json_to_microservice)

        data_json = rabbitmq.response.decode('utf-8')
        data_json = json.loads(data_json)

        print(data_json)

        write_serializer = InterviewCreateSerializer(data=request.data)
        write_serializer.is_valid(raise_exception=True)
        self.perform_create(write_serializer)

        read_serializer = InterviewDetailSerializer(write_serializer.instance)

        return Response(read_serializer.data, status=status.HTTP_201_CREATED)


class InterviewDetailView(generics.RetrieveUpdateAPIView):
    queryset = Interview.objects.all()
    serializer_class = InterviewDetailSerializer

    def partial_update(self, request, *args, **kwargs):
        partial = kwargs.pop('partial', True)
        instance = self.get_object()
        write_serializer = InterviewCreateSerializer(
            instance, data=request.data, partial=partial
        )
        write_serializer.is_valid(raise_exception=True)
        self.perform_update(write_serializer)

        if getattr(instance, '_prefetched_objects_cache', None):
            instance._prefetched_objects_cache = {}

        read_serializer = InterviewDetailSerializer(instance)

        return Response(read_serializer.data)


class CriteriaCreateListView(generics.ListCreateAPIView):
    queryset = Criteria.objects.all()
    serializer_class = CriteriaSerializer
    filter_fields = ('department',)
