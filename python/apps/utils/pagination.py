from rest_framework.pagination import PageNumberPagination


class SizedPageNumberPagination(PageNumberPagination):
    page_size_query_param = 'size'
