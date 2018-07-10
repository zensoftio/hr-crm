FROM python:3

WORKDIR /code
# Without these lines it fails to build Pillow
RUN ln -s /usr/lib/x86_64-linux-gnu/libz.so /lib/
RUN ln -s /usr/lib/x86_64-linux-gnu/libjpeg.so /lib/
ADD requirements.txt .
RUN pip install -r requirements.txt

ADD . /code/

EXPOSE 8000
