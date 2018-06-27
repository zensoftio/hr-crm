FROM python:3

WORKDIR /code
ADD requirements.txt .
RUN pip install -r requirements.txt

ADD . /code/
EXPOSE 8000

CMD sleep 5 && python manage.py runserver 0.0.0.0:8000