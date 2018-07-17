var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : new P(function (resolve) { resolve(result.value); }).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
const fs = require('fs');
const readline = require('readline');
const { google } = require('googleapis');
var Base64 = require('js-base64').Base64;
var exports = module.exports = {};
const dotenv = require('dotenv').config();
const stringify = require('json-stringify-safe');
const cloud = require('../google-cloud/cloud.js');
var SCOPES = [
    'https://mail.google.com/',
    'https://www.googleapis.com/auth/gmail.modify',
    'https://www.googleapis.com/auth/gmail.compose',
    'https://www.googleapis.com/auth/gmail.send'
];
const TOKEN_PATH = 'credentials.json';
exports.sendMessageH = (data, recipient) => __awaiter(this, void 0, void 0, function* () {
    return authorize(sendMessage, data, recipient);
});
exports.getAllMessages = (date) => __awaiter(this, void 0, void 0, function* () {
    return authorize(getMessagesByDate, date);
});
const authorize = (callback, data, recipient) => __awaiter(this, void 0, void 0, function* () {
    const client_id = process.env['CLIENT_ID'];
    const client_secret = process.env['CLIENT_SECRET'];
    const redirect_uris = process.env['REDIRECT_URIS'];
    const oAuth2Client = new google.auth.OAuth2(client_id, client_secret, redirect_uris);
    let token = null;
    try {
        token = fs.readFileSync(TOKEN_PATH);
    }
    catch (err) {
        return getNewToken(oAuth2Client, callback, data, recipient);
    }
    oAuth2Client.setCredentials(JSON.parse(token));
    return callback(oAuth2Client, data, recipient);
});
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
            if (err)
                return callback(err);
            oAuth2Client.setCredentials(token);
            fs.writeFile(TOKEN_PATH, JSON.stringify(token), (err) => {
                if (err)
                    return console.error(err);
                console.log('Token stored to', TOKEN_PATH);
            });
            callback(oAuth2Client, data, recipient);
        });
    });
};
const getMessagesByDate = (auth, date, callback) => __awaiter(this, void 0, void 0, function* () {
    const gmail = google.gmail({ version: 'v1', auth });
    date = 1530406861;
    const query = `is:inbox AND after: ${date}`;
    const getMail = yield gmail.users.messages.list({
        userId: 'me',
        q: query,
    });
    if (getMail.data.messages) {
        return getMessageById(getMail.data.messages, gmail);
    }
    else {
        return [];
    }
});
const getMessageById = (messages, gmail, callback) => __awaiter(this, void 0, void 0, function* () {
    var msgList = [];
    const results = yield Promise.all(messages.map((msg) => __awaiter(this, void 0, void 0, function* () {
        const singleMessage = gmail.users.messages.get({
            userId: 'me',
            id: msg.id,
        });
        const message = yield singleMessage;
        msgList.push(message);
    })));
    return getEmailAttachmentId(msgList, gmail);
});
const getEmailAttachmentId = (msgList, gmail, callback) => __awaiter(this, void 0, void 0, function* () {
    var emailWithAttachment = [];
    msgList.forEach((message, index) => {
        var msgId = message.data.id;
        message.data.payload.headers.forEach((email, msgDataIndex) => {
            if (email.name.toUpperCase() == "FROM") {
                var sendedEmail;
                if (email.value.includes("<")) {
                    sendedEmail = email.value.substring(email.value.lastIndexOf("<") + 1, email.value.lastIndexOf(">"));
                }
                else {
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
                    message.data.payload.parts.forEach((part, partIndex) => {
                        if (part.body.attachmentId) {
                            attachmentIds.push(part.body.attachmentId);
                            fileNames.push(part.filename);
                            checkParts = true;
                        }
                    });
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
        });
    });
    return getAttachmentById(emailWithAttachment, gmail);
});
const getAttachmentById = (msgList, gmail) => __awaiter(this, void 0, void 0, function* () {
    var resultData = [];
    const messageList = yield Promise.all(msgList.map((message, index) => __awaiter(this, void 0, void 0, function* () {
        var query = {};
        query['email'] = message.email;
        if (message.attachmentIds.length > 0) {
            var fileNames = [];
            var base64 = [];
            const attachments = yield Promise.all(message.attachmentIds.map((id, idIndex) => __awaiter(this, void 0, void 0, function* () {
                const attachment = gmail.users.messages.attachments.get({
                    id: id,
                    messageId: message.messageId,
                    userId: 'me',
                });
                const waitAtt = yield attachment;
                base64.push(waitAtt.data.data);
                fileNames.push(message.fileNames[idIndex]);
            })));
            query['base64'] = base64;
            query['fileNames'] = fileNames;
            const upload = yield cloud.uploadToStrage(query);
            resultData.push(upload);
        }
        else {
            resultData.push(query);
        }
    })));
    return resultData;
});
const sendMessage = (auth, data, recipient) => __awaiter(this, void 0, void 0, function* () {
    var raw = makeBody(data, recipient);
    const sentMessage = yield gmail.users.messages.send({
        auth: auth,
        userId: 'me',
        resource: {
            raw: raw
        }
    });
    return sentMessage.status;
});
function makeBody(data, recipient) {
    var boundary = "__myapp__";
    var nl = "\n";
    let filename = data.attachments[0].name;
    let type = recipient.type + ": " + recipient.email;
    let structure = [];
    data.attachments.forEach((attachment) => {
        structure.push("--" + boundary, "Content-Type: Application/octet-stream; name=" + attachment.name, "Content-Disposition: attachment; filename=" + attachment.name, "Content-Transfer-Encoding: base64" + nl, attachment.data, "--" + boundary);
    });
    const body = data.content.replace(/NAME/g, recipient.name);
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
    ].join('\n');
    var encodedMail = new Buffer(str).toString("base64").replace(/\+/g, '-').replace(/\//g, '_');
    return encodedMail;
}
//# sourceMappingURL=gmailapi.js.map