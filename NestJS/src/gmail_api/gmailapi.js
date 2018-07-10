const fs = require('fs');
const readline = require('readline');
const {google} = require('googleapis');
var Base64 = require('js-base64').Base64;
var exports = module.exports = {};
const dotenv = require('dotenv').config();
const stringify = require('json-stringify-safe')
const cloud = require('../google-cloud/cloud.js')

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

/**
 * Create an OAuth2 client with the given credentials, and then execute the
 * given callback function.
 * @param {Object} credentials The authorization client credentials.
 * @param {function} callback The callback to call with the authorized client.
 */

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

/**
 * Lists the labels in the user's account.
 *
 * @param {google.auth.OAuth2} auth An authorized OAuth2 client.
 */


const getMessagesByDate = async (auth,date,callback) => {
  const gmail = google.gmail({version: 'v1', auth});
  date = new Date(date).getTime() / 1000;

  const query = `is:inbox AND after: ${date}`;

  const getMail = await gmail.users.messages.list({
    userId: 'me',
    q: query,
  })

  return await getMessageById(getMail.data.messages,gmail);
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
  return await getEmailAttachmentId(msgList,gmail);
}

const getEmailAttachmentId = async (msgList,gmail,callback) => {
    var emailWithAttachment = [];
    msgList.forEach( (message,index) => {
      var msgId = message.data.id;
      message.data.payload.headers.forEach( (email,msgDataIndex) => {
        if (email.name.toUpperCase() == "FROM") {
          var sendedEmail;
          if (email.value.includes("<")){
            sendedEmail = email.value.substring(
            email.value.lastIndexOf("<") + 1,
            email.value.lastIndexOf(">"));
          }else{
            sendedEmail = email.value;
          }
          var query = {};
          query['email'] = sendedEmail;
          query['messageId'] = msgId;
          query['attachmentIds'] = [];
          query['fileNames'] = [];
          var attachmentIds = [];
          var fileNames = [];
          var checkParts = false;
          var checkAtt = false;
          if (message.data.payload.parts) {
            message.data.payload.parts.forEach( (part,partIndex) => {
              if (part.body.attachmentId) {
                attachmentIds.push(part.body.attachmentId)
                fileNames.push(part.filename)
                checkParts = true;
              }
            })
            if (checkParts) {
              query['attachmentIds'] = attachmentIds;
              query['fileNames'] = fileNames;
              emailWithAttachment.push(query);
            }
          }
          else {
            checkAtt = true;
            emailWithAttachment.push(query);
          }
          if (!checkParts && !checkAtt) {
            emailWithAttachment.push(query);
          }
        }
      })
    });
    return await getAttachmentById(emailWithAttachment,gmail);
}

const getAttachmentById = async (msgList,gmail) => {
  var resultData = [];
  const  messageList = await Promise.all(msgList.map(async (message,index) => {
    var query = {};
    query['email'] = message.email;
    if (message.attachmentIds.length > 0) {
      var fileNames = [];
      var base64 = [];
      const attachments = await Promise.all(message.attachmentIds.map(async (id,idIndex) => {
        const attachment = gmail.users.messages.attachments.get({
          id: id,
          messageId: message.messageId,
          userId: 'me',
        });
        const waitAtt = await attachment;
        base64.push(waitAtt.data.data);
        fileNames.push(message.fileNames[idIndex]);
      }));
      query['base64'] = base64;
      query['fileNames'] = fileNames;
      const upload = await cloud.uploadToStrage(query);
      resultData.push(upload);
    }
    else {
      resultData.push(query);
    }
  }));
  return resultData;
}


const sendMessage = async (auth, data, recipient) => {
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

const makeBody = (data,recipient) => {
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
        "from: dasha.ree1@gmail.com",
        "subject: " + data.subject,
        "Content-Type: multipart/alternate; boundary=" + boundary + nl,
        "--" + boundary,
        "Content-Type: text/plain; charset=UTF-8",
        "Content-Transfer-Encoding: 7bit" + nl,
        body + nl,
        "--" + boundary,
        "--" + boundary,
        "Content-Type: Application/docx; name=a.docx",
        'Content-Disposition: attachment; filename=a.docx',
        "Content-Transfer-Encoding: base64" + nl,
        attach,
        "--" + boundary + "--"
    ].join("\n");

    var encodedMail = new Buffer(str).toString("base64").replace(/\+/g, '-').replace(/\//g, '_');
    return encodedMail;
}


//function to send message to multiple recipients at once
const defineTypeOfRecipients = (data) => {
  let i;
  let to = [], cc = [], bcc = [];
  for(i = 0; i < data.recipients.length; i++){
    switch(data.recipients[i].type){
      case "to":
        to.push(data.recipients[i].email)
        break;
      case "cc":
        cc.push(data.recipients[i].email)
        break;
      case "bcc":
        bcc.push(data.recipients[i].email)
        break;
      default:
        break;
    }
  }
  //put arrays' content into strings
  let listTos = to.join(', '), listBCCs = bcc.join(', '), listCCs = cc.join(', ');
  return [listTos, listCCs, listBCCs];
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
