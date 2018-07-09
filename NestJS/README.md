# nest-js

## Description

Email/Calendar Microservice for HR-CRM System.

## Installation

```bash
$ npm install
```

## Running the app

```bash
# development
$ npm run start

# watch mode
$ npm run start:dev

# production mode
npm run start:prod
```

## Usage

Create an `.env` file with client id, client secret and redirect_uris for the app. To get those go to https://console.developers.google.com/start/api?id=gmail to create or select a project in the Google Developers Console.

```bash
CLIENT_ID=
CLIENT_SECRET=
REDIRECT_URIS=
```
## Google Cloud Storage
1) Google account as Developer
2) Select or Create a Cloud Platform project
3) Enable billing for your project
4) Create Storage
5) Create Bucket
6) Enable the Google Cloud Storage API
7) Get projectId and service-account
