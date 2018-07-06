from rest_framework import status


class ListTestMixin(object):
    """
        Mixin for testing creating Model instance

        Usage:
            url - endpoint e.g. '/candidates/'
            model - e.g Candidate
            serializer_class - e.g. CandidateSerializer
    """

    model = None
    serializer = None

    def test_get_list(self):
        url = '/' + str(self.model._meta.verbose_name_plural) + '/'
        response = self.client.get(url)
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

    model = None
    serializer = None
    request_body = {}

    def test_creation(self):
        url = '/' + str(self.model._meta.verbose_name_plural) + '/'
        response = self.client.post(url, self.request_body)
        self.assertEqual(201, response.status_code)
        self.assertEqual(1, self.model.objects.count())


class GetInstanceTestMixin(object):
    """
        Mixin for testing receiving an instance separately

        Usage:
            model - e.g Candidate
            serializer - e.g. CandidateSerializer

        ! Important:
            You must override def setUp(self):
            and create Model Instance e.g. Candidate.object.create()
            And Create all Related objects(Related Fields in 'model')
    """
    model = None
    serializer = None

    def test_instance(self):
        response = self.client.get(self.instance.get_absolute_url())
        serializer = self.serializer(self.instance)

        self.assertEqual(response.status_code, status.HTTP_200_OK)
        self.assertEqual(response.data, serializer.data)


class UpdateTestMixin(object):
    """
        Mixin for testing updating Model instance

        Usage:
            model = e.g Candidate
            serializer = e.g CandidateSerializer

        ! Important:
            You must override def setUp(self):
            and get Model Instance e.g. Candidate.object.get(pk=1)
            and set data for fields of instance e.g
                                                    self.update_data = {
                                                        "field_1": "data",
                                                        "field_2": "data"
                                                        etc.
                                                    }
    """
    model = None
    serializer = None
    update_data = {}

    def test_update(self):
        response = self.client.patch(self.instance.get_absolute_url(), self.update_data)

        self.assertEqual(response.status_code, status.HTTP_200_OK)