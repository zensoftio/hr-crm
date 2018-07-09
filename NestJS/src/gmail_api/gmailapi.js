const fs = require('fs');
const readline = require('readline');
const {google} = require('googleapis');
var Base64 = require('js-base64').Base64;
var exports = module.exports = {};
const dotenv = require('dotenv').config();
const stringify = require('json-stringify-safe')

// If modifying these scopes, delete credentials.json.
var SCOPES = [
  'https://mail.google.com/',
  'https://www.googleapis.com/auth/gmail.modify',
  'https://www.googleapis.com/auth/gmail.compose',
  'https://www.googleapis.com/auth/gmail.send'
];
const TOKEN_PATH = 'credentials.json';

exports.sendMessageH = async (data, recipient) => {
    return await authorize(sendMessage, data, recipient);
}

exports.getAllMessages = async (date) => {
  return await authorize(getMessagesByDate,date);
}

const authorize = async (callback, data, recipient) => {
  const client_id = process.env['CLIENT_ID'];
  const client_secret = process.env['CLIENT_SECRET'];
  const redirect_uris = process.env['REDIRECT_URIS'];

  const oAuth2Client = new google.auth.OAuth2(client_id, client_secret, redirect_uris);

  const token = fs.readFileSync(TOKEN_PATH)
  if (!token) return getNewToken(oAuth2Client, callback, data, recipient);
  oAuth2Client.setCredentials(JSON.parse(token));

  const result = await callback(oAuth2Client, data, recipient)
  return result
}



 const getNewToken = (oAuth2Client, callback, data, recipient) => {
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
       fs.writeFile(TOKEN_PATH, JSON.stringify(token), (err) => {
         if (err) return console.error(err);
         console.log('Token stored to', TOKEN_PATH);
       });
       callback(oAuth2Client, data, recipient);
     });
   });
 }

const getMessagesByDate = async (auth,date,callback) => {

  const gmail = google.gmail({version: 'v1', auth});
  date = new Date(date).getTime() / 1000;
  const query = 'is:inbox AND after: ${date}';

  const messageList = await gmail.users.messages.list({
    userId: 'me',
    q: query,
  })

  const messages = await getMessageById(messageList.data.messages,gmail);
  return messages;
}

const getMessageById = async (messages,gmail,callback) => {
  var msgList = [];
  const results = await Promise.all(messages.map(async (msg) => {
    const singleMessage = gmail.users.messages.get({
      userId: 'me',
      id: msg.id,
    });
    const message = await singleMessage;
    msgList.push(message);
  }));
  return getEmailAttachmentId(msgList);
}

const getEmailAttachmentId = async (msgList) => {
  var emailWithAttachment = [];
  msgList.forEach( (msgListElement,msgListIndex) => {
    msgListElement.data.payload.headers.forEach( (emailElement,emailIndex) => {
      if (emailElement.name.toUpperCase() === "FROM") {
        var email;
        if (emailElement.value.includes("<")){
          email = emailElement.value.substring(
          emailElement.value.lastIndexOf("<") + 1,
          emailElement.value.lastIndexOf(">"));
        }else{
          email = emailElement.value;
        }
        var query = {};
        query['email'] = email;
        var attIds = [];
        if (msgListElement.data.payload.parts) {
          msgListElement.data.payload.parts.forEach( (partsElement,partsIndex) => {
            if (partsElement.body.attachmentId) {
              attIds.push(partsElement.body.attachmentId)
            }
            query['attachment'] = attIds;
            emailWithAttachment.push(query)
          })
        }
      }
    })
  });
  return emailWithAttachment;
}


const sendMessage = async (auth, data, recipient) => {
  const gmail = google.gmail({version: 'v1', auth})
  var raw = makeBody(data, recipient);

  const sentMessage = await gmail.users.messages.send({
    auth: auth,
    userId: 'me',
    resource: {
      raw: raw
    }
  })
  return sentMessage.status
}


function makeBody(data, recipient) {
  var boundary = "__myapp__";
  var nl = "\n";
  let filename = data.attachments[0].name;
  let type = recipient.type + ": " + recipient.email
  let structure = []
  data.attachments.forEach((attachment) => {
    structure.push(
      "--" + boundary,
      "Content-Type: Application/octet-stream; name=" + attachment.name,
      "Content-Disposition: attachment; filename=" + attachment.name,
      "Content-Transfer-Encoding: base64" + nl,
      attachment.data,
      "--" + boundary,)
  });

  const body = data.content.replace(/NAME/g, recipient.name)
  var str = [
          "MIME-Version: 1.0",
          "Content-Transfer-Encoding: 7bit",
          type,
          "from: shisyr2106@gmail.com",
          "subject: " + data.subject,
          "Content-Type: multipart/alternate; boundary=" + boundary + nl,
          "--" + boundary,
          "Content-Type: text/plain; charset=UTF-8",
          "Content-Transfer-Encoding: 7bit" + nl,
          body + nl,
          "--" + boundary,
          structure.join('\n')
  ].join('\n')
  var encodedMail = new Buffer(str).toString("base64").replace(/\+/g, '-').replace(/\//g, '_');
  return encodedMail;
}
