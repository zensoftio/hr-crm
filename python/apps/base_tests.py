from rest_framework import status


class ListTestMixin(object):
    """
        Mixin for testing creating Model instance

        Usage:
            url - endpoint e.g. '/candidates/'
            model - e.g Candidate
            serializer_class - e.g. CandidateSerializer
    """

    url = None
    model = None
    serializer = None

    def test_get_list(self):
        response = self.client.get(self.url)
        queryset = self.model.objects.all()
        serializer = self.serializer(queryset, many=True)

        self.assertEqual(response.status_code, 200)
        self.assertEqual(response.data['results'], serializer.data)


class CreateTestMixin(object):
    """
        Mixin for testing creating Model instance

        Usage:
            url - endpoint e.g. '/candidates/'
            model - e.g Candidate
            serializer_class - e.g. CandidateSerializer
    """
    url = None
    model = None
    serializer = None
    request_body = {}

    def test_creation(self):
        response = self.client.post(self.url, self.request_body)
        self.assertEqual(201, response.status_code)
        self.assertEqual(1, self.model.objects.count())


class GetInstanceTestMixin(object):
    """
    Mixin for testing concrete Candidate instance

    Usage:
        url - endpoint e.g. '/candidates/'
        model - e.g Candidate
        serializer - e.g. CandidateSerializer

    ! Important:
        You must override def setUp(self):
        and create Model Instance e.g. Candidate.object.create()
        And Create all Related objects(Related Fields in 'model')
    """
    url = None
    model = None
    serializer = None

    def test_instance(self):
        url = self.url + str(self.instance.id)
        response = self.client.get(url)
        serializer = self.serializer(self.instance)

        self.assertEqual(response.status_code, status.HTTP_200_OK)
        self.assertEqual(response.data, serializer.data)
