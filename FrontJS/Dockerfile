FROM node:9.11

COPY package.json /app/

RUN cd /app && npm install

COPY . /app

WORKDIR /app
