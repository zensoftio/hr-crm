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
## Using phone notifications
Login or Sign up in the https://www.nexmo.com.

Using your NEXMO API_KEY and API_SECRET, available from the dashboard getting started page, you can now send an SMS message:
```bash
curl -X "POST" "https://rest.nexmo.com/sms/json" \
   -d "from=Acme Inc" \
   -d "text=A text message sent using the Nexmo SMS API" \
   -d "to=TO_NUMBER" \
   -d "api_key=NEXMO_API_KEY" \
   -d "api_secret=NEXMO_API_SECRET"
```
```bash
const Nexmo = require('nexmo');

const nexmo = new Nexmo({
   apiKey: 'API_KEY',
   apiSecret: 'API_SECRET'
});

const from = 'Nexmo';
const to = 'TO_NUMBER';
const text = 'A text message sent using the Nexmo SMS API';

nexmo.message.sendSms(from, to, text, (error, response) => {
   if(error) {
     throw error;
   } else if(response.messages[0].status != '0') {
     console.error(response);
     throw 'Nexmo returned back a non-zero status';
   } else {
     console.log(response);
   }
});
You need to put API_KEY and API_SECRET to NestJS/src/Event/phone_notification/sms.js
