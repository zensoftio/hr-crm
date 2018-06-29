const fs = require('fs');
const readline = require('readline');
const {google} = require('googleapis');
const request = require('request');
const dotEnv = require('dotenv').config({path: '/Users/Mukhamed/Downloads/Zensoft/main/hr-crm/NestJS/.env'});
// If modifying these scopes, delete credentials.json.
const SCOPES = ['https://www.googleapis.com/auth/calendar'];

let auth = null;
let calendar = null;
const TOKEN_PATH = 'credentials.json';

  var event = {
  'summary': "sdosdf",
  'location': 'sffsdf',
  'description': 'sdfsf',
  'start': {
    'dateTime': '2018-06-29T11:31:00+06:00',
    'timeZone': 'America/Los_Angeles',
  },
  'end': {
    'dateTime': '2018-06-29T11:31:00+06:00',
    'timeZone': 'America/Los_Angeles',
  },
  'recurrence': [
    'RRULE:FREQ=DAILY;COUNT=1'
  ],
  'attendees': [],
  'reminders': {
    'useDefault': false,
    'overrides': [
      {'method': 'email', 'minutes': 5},
      {'method': 'popup', 'minutes': 5},
    ],
  },
};
module.exports = {
  run: function(json, callback){
    try {
      authorize(function(auth){
        calendar = google.calendar({version: 'v3', auth});
        let jsonObj = json.content;
        if(jsonObj.begin_time && jsonObj.end_time && jsonObj.description && jsonObj.location){
          event.location = jsonObj.location;
          event.description = jsonObj.description;
          event.start.dateTime = jsonObj.begin_time;
          event.end.dateTime = jsonObj.end_time;
          for(let i = 0;i < jsonObj.email.length;i++){
            event.attendees.push({'email': jsonObj.email[i]});
          }
        }
        if(json.description == "create"){
          create(jsonObj, function(data){
            callback(data);
          });
        }
        // else if(json.description == "update"){
        //   update(json, function(data){
        //     callback(data);
        //   })
        // }
      });
    } catch (err) {
      return console.log('Error loading client secret file:', err);
    }
  }
}

function create(json, callback){

  calendar.events.insert({auth: auth, calendarId: 'primary', resource: event}, function(err, event) {
    if (err) {
      console.log('There was an error contacting the Calendar service: ' + err);
      return;
    }
    console.log(JSON.parse(event.config.data).summary)
    console.log('Event created: %s', event);
    //console.log(event.data);
    callback(event.data)
  });
}


function authorize(callback) {
  const client_secret = process.env.client_secret;
  const client_id = process.env.client_id;
  const redirect_uris = process.env.redirect_uris;
  let token = {};
  const oAuth2Client = new google.auth.OAuth2(
      client_id, client_secret, redirect_uris);

  try {
    token = fs.readFileSync(TOKEN_PATH);
  } catch (err) {
    return getAccessToken(oAuth2Client, callback);
  }
  oAuth2Client.setCredentials(JSON.parse(token));
  callback(oAuth2Client);
}


function getAccessToken(oAuth2Client, callback) {
  const authUrl = oAuth2Client.generateAuthUrl({
    access_type: 'offline',
    scope: SCOPES,
  });
  console.log('Authorize this app by visiting this url:', authUrl);

  const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout,
  });
  rl.question('Enter the code from that page here: ', (code) => {
    rl.close();
    oAuth2Client.getToken(code, (err, token) => {
      if (err) return callback(err);
      oAuth2Client.setCredentials(token);
      // Store the token to disk for later program executions
      try {
        fs.writeFileSync(TOKEN_PATH, JSON.stringify(token));
        console.log('Token stored to', TOKEN_PATH);
      } catch (err) {
        console.error(err);
      }
      callback(oAuth2Client);
    });
  });
}
 //
 // function listEvents(auth) {
 //   const calendar = google.calendar({version: 'v3', auth});
 //

 //
 // // calendar.events.insert({
 // //   auth: auth,
 // //   calendarId: 'primary',
 // //   resource: event,
 // // }, function(err, event) {
 // //   if (err) {
 // //     console.log('There was an error contacting the Calendar service: ' + err);
 // //     return;
 // //   }
 // //   console.log(JSON.parse(event.config.data).summary)
 // //   console.log('Event created: %s', event);
 // // });
 //   calendar.events.list({
 //     calendarId: 'primary',
 //     timeMin: (new Date()).toISOString(),
 //     maxResults: 10,
 //     singleEvents: true,
 //     orderBy: 'startTime',
 //   }, (err, {data}) => {
 //     if (err) return console.log('The API returned an error: ' + err);
 //     const events = data.items;
 //     if (events.length) {
 //       console.log('Upcoming 10 events:');
 //       events.map((event, i) => {
 //         console.log(event)
 //         const start = event.start.dateTime || event.start.date;
 //         console.log(`${start} - ${event.summary}`);
 //       });
 //     } else {
 //       console.log('No upcoming events found.');
 //     }
 //   });
 // }
 //
 //
 // calendar.events.list({
 //   calendarId: 'primary',
 //   timeMin: (new Date()).toISOString(),
 //   maxResults: 10,
 //   singleEvents: true,
 //   orderBy: 'startTime',
 // }, (err, {data}) => {
 //   if (err) return console.log('The API returned an error: ' + err);
 //   const events = data.items;
 //   if (events.length) {
 //     console.log('Upcoming 10 events:');
 //     events.map((event, i) => {
 //       console.log(event)
 //       const start = event.start.dateTime || event.start.date;
 //       console.log(`${start} - ${event.summary}`);
 //     });
 //   } else {
 //     console.log('No upcoming events found.');
 //   }
 // });

 // //
 // {'email': 'talantbekov123@gmail.com'},
 // {'email': 'shisyr96@mail.ru'}
