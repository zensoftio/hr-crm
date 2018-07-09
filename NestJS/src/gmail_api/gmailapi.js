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

authorize = async (callback, data, recipient) => {
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


/**
 * Get and store new token after prompting for user authorization, and then
 * execute the given callback with the authorized OAuth2 client.
 * @param {google.auth.OAuth2} oAuth2Client The OAuth2 client to get token for.
 * @param {getEventsCallback} callback The callback for the authorized client.
 */

 getNewToken = (oAuth2Client, callback, data, recipient) => {
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


getMessagesByDate = async (auth,date,callback) => {

  const gmail = google.gmail({version: 'v1', auth});
  // date = new Date(date).getTime() / 1000;
  // console.log("DATE IS: " + date);
  // const query = 'is:inbox AND after: ${date}';
  date = 1530748800;
  // date = 1530579600;

  const query = `is:inbox AND after: ${date}`;

  const getMail = await gmail.users.messages.list({
    userId: 'me',
    q: query,
  })

  const messages = await getMessageById(getMail.data.messages,gmail);
  return messages;
}

getMessageById = async (messages,gmail,callback) => {
  var msgList = [];
  const results = await Promise.all(messages.map(async (msg) => {
    const getMessage = gmail.users.messages.get({
      userId: 'me',
      id: msg.id,
    });
    const message = await getMessage;
    msgList.push(message);
  }));
  return await getEmailAttachmentId(msgList,gmail);
}

getEmailAttachmentId = async (msgList,gmail,callback) => {
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
          var check = false;
          if (message.data.payload.parts) {
            message.data.payload.parts.forEach( (part,partIndex) => {
              if (part.body.attachmentId) {
                attachmentIds.push(part.body.attachmentId)
                fileNames.push(part.filename)
                check = true;
              }
            })
            if (check) {
              query['attachmentIds'] = attachmentIds;
              query['fileNames'] = fileNames;
              emailWithAttachment.push(query);
            }
          }
          else {
            emailWithAttachment.push(query);
          }
        }
      })
    })
    return await getAttachmentById(emailWithAttachment,gmail);
}

getAttachmentById = async (msgList,gmail) => {
      // msgWithCloudUrl = [];
      // const msgList1 = await Promise.all(msgList.map(async (msg) => {
      //   var query = {};
      //   query['email'] = msg.email;
      //   if (msg.attachmentIds.length > 0) {
      //     var arrayAttList = [];
      //     const attachmentList = await Promise.all(msg.attachmentIds.map(async (att) => {
      //       const attachment = gmail.users.messages.attachments.get({
      //         id: att,
      //         messageId: msg.messageId,
      //         userId: 'me',
      //       });
      //       var a = await attachment;
      //       arrayAttList.push(a.data.size,msg.fileNames)
      //     }));
      //     var attList = await attachmentList;
      //     console.log(arrayAttList);
      //   }
      // }));
      // await msgList1;
      //

      msgList.forEach( (messages,index) => {
        if (messages.attachmentIds.length > 0) {
          messages.attachmentIds.forEach( async(id,attIndex) => {
              const attachment = gmail.users.messages.attachments.get({
                id: id,
                messageId: messages.messageId,
                userId: 'me',
              });
              const a = await attachment;
              console.log(a.data.size);
              console.log("--------");
          })
        }
      })

    return "YES"
}

uploadAttachmentToGoogleCloud = async (list) => {

}

sendMessage = async (auth, data, recipient) => {
  // const gmail = google.gmail({version: 'v1', auth})
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

makeBody = (data,recipient) => {
  var boundary = "__myapp__";
  var nl = "\n";
  let fileToAttach = '/home/reedvl/Downloads/test.docx';
  var attach = new Buffer(fs.readFileSync(fileToAttach)) .toString("base64");

  let type = recipient.type + ": " + recipient.email

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
defineTypeOfRecipients = (data) => {
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
}
