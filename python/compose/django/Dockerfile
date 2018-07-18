FROM python:3.6-alpine

ENV PYTHONUNBUFFERED 1

RUN apk update \
  && apk add --virtual build-deps gcc python3-dev musl-dev \
  && apk add postgresql-dev \
  && apk add jpeg-dev zlib-dev freetype-dev lcms2-dev openjpeg-dev tiff-dev tk-dev tcl-dev \
  && apk add libffi-dev py-cffi

COPY ./requirements.txt /requirements.txt
RUN pip install --no-cache-dir -r /requirements.txt \
    && rm -f /requirements.txt

COPY ./compose/django/entrypoint /entrypoint
RUN sed -i 's/\r//' /entrypoint
RUN chmod +x /entrypoint

COPY ./compose/django/start /start
RUN sed -i 's/\r//' /start
RUN chmod +x /start

COPY ./compose/django/listen /listen
RUN sed -i 's/\r//' /listen
RUN chmod +x /listen

COPY . /app

WORKDIR /app

ENTRYPOINT ["/entrypoint"]
