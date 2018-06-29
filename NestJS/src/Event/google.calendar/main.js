const fs = require('fs');
const readline = require('readline');
const {google} = require('googleapis');
const request = require('request');
// If modifying these scopes, delete credentials.json.
const SCOPES = ['https://www.googleapis.com/auth/calendar'];

const TOKEN_PATH = 'credentials.json';
//const receive = require('./receive');
// Load client secrets from a local file.
  var event = {
  'summary': "MSsfdkskfskfL",
  'location': '800 Howard St., San Francisco, CA 94103',
  'description': 'A chance to hear more about Google\'s developer products.',
  'start': {
    'dateTime': 'null',
    'timeZone': 'America/Los_Angeles',
  },
  'end': {
    'dateTime': 'null',
    'timeZone': 'America/Los_Angeles',
  },
  'recurrence': [
    'RRULE:FREQ=DAILY;COUNT=1'
  ],
  'attendees': [
    {'email': "null"}
  ],
  'reminders': {
    'useDefault': false,
    'overrides': [
      {'method': 'email', 'minutes': 5},
      {'method': 'popup', 'minutes': 5},
    ],
  },
};

module.exports = {
  run: function(json){
    try {
      const content = fs.readFileSync('/Users/Mukhamed/Downloads/pro/js-models/src/Event/client_secret.json');
      doEvents(content, json);
    } catch (err) {
      return console.log('Error loading client secret file:', err);
    }
  }
}

function doEvents(content, json){
  authorize(JSON.parse(content), function listEvents(auth){
      const calendar = google.calendar({version: 'v3', auth});
      console.log(json);
      event.description = json.description;
      event.attendees[0].email = json.email[0];
      for(var i = 1;i < json.email.length;i++){
        event.attendees.push({'email': json.email[i]});
      }
      event.start.dateTime = json.begin_time;
      event.end.dateTime = json.begin_time;
      console.log(event);
      calendar.events.insert({
        auth: auth,
        calendarId: 'primary',
        resource: event,
      }, function(err, event) {
        if (err) {
          console.log('There was an error contacting the Calendar service: ' + err);
          return;
        }
        console.log(JSON.parse(event.config.data).summary)
        console.log('Event created: %s', event);
      });
  });
}


function authorize(credentials, callback) {
  const {client_secret, client_id, redirect_uris} = credentials.installed;
  let token = {};
  const oAuth2Client = new google.auth.OAuth2(
      client_id, client_secret, redirect_uris[0]);

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
