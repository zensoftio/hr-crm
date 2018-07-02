class ListTestMixin(object):
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
    url = None
    model = None
    serializer = None
    request_body = {}

    def test_creation(self):
        response = self.client.post(self.url, self.request_body)
        self.assertEqual(201, response.status_code)
        self.assertEqual(1, self.model.objects.count())
