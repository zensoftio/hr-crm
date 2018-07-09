const fs = require('fs');
const readline = require('readline');
const {google} = require('googleapis');
const request = require('request');
const dotEnv = require('dotenv').config({path: '/Users/Mukhamed/Downloads/Zensoft/main/hr-crm/NestJS/.env'});
// If modifying these scopes, delete credentials.json.
const SCOPES = ['https://www.googleapis.com/auth/calendar', 'https://mail.google.com/'];

let auth = null;
let calendar = null;
const TOKEN_PATH = 'credentials.json';

var event = {
  'summary': 'Interview',
  'location': '',
  'description': '',
  'start': {
    'dateTime': '',
    'timeZone': 'America/Los_Angeles',
  },
  'end': {
    'dateTime': '',
    'timeZone': 'America/Los_Angeles',
  },
  'recurrence': [
    'RRULE:FREQ=DAILY;COUNT=1'
  ],
  'singleEvents': true,
  'sendNotifications': true,
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
        let jsonObj = json.body;
        if(json.title === "create" && hasAllObjects(jsonObj)){
            create(jsonObj, function(err, data){
              callback(err, data);
            });
        }
        else if(json.title === "update" && hasAllObjects(jsonObj)){
            update(jsonObj, function(err, data){
              callback(err, data);
            });
        }
        else if(json.title === "remove" && hasAllObjects(jsonObj)){
            remove(jsonObj, function(err, data){
              if(err){
                callback(err, "");
              }
              callback("", data);
            })
        }
      });
    } catch (err) {
      callback('Error loading client secret file:' + err, '');
    }
  }
}

function hasAllObjects(jsonObj){
  if(jsonObj.begin_time && jsonObj.end_time && jsonObj.description && jsonObj.location){
    event.attendees = [];
    event.location = jsonObj.location;
    event.description = jsonObj.description;
    event.start.dateTime = jsonObj.begin_time;
    event.end.dateTime = jsonObj.end_time;
    for(let i = 0;i < jsonObj.email.length;i++){
      event.attendees.push({'email': jsonObj.email[i]});
    }
    return event.attendees.length > 0 ? true : false;
  }
  return false;
}
function create(json, callback){
  calendar.events.insert({auth: auth, calendarId: 'primary', resource: event}, function(err, response) {
    if (err) {
      callback("Cannot create because of invalid email, begin_time, or end_time!", '');
    }
    else{
      callback(undefined, response.data)
    }

  });
}

function update(json, callback){
  calendar.events.update({
          auth: auth,
          calendarId: 'primary',
          eventId: json.id_event,
          resource: event
  }, function(err, response){
    if(err) {
      callback('Cannot update because of invalid email, begin_time, or end_time!', '');
    }
    else{
      callback('', response.data);
    }
  });
}
function remove(json, callback){
  calendar.events.delete(
    {
      auth: auth,
      calendarId: 'primary',
      eventId: json.id_event
    },function(err, response){
      if(err){
        callback("There is no such event in the google calendar!", "");
      }
      else{
        callback("", "Deleted!");
      }
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
