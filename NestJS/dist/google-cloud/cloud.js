var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : new P(function (resolve) { resolve(result.value); }).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
const Promise = require('bluebird');
const GoogleCloudStorage = Promise.promisifyAll(require('@google-cloud/storage'));
const BUCKET_NAME = 'candidate-cvs';
const stream = require('stream');
const storage = GoogleCloudStorage({
    projectId: 'hr-zensoft',
    keyFilename: 'service-account.json'
});
const myBucket = storage.bucket(BUCKET_NAME);
exports.uploadToStrage = (data) => __awaiter(this, void 0, void 0, function* () {
    var query = {
        'email': data.email
    };
    query['url'];
    var cloudUrl = [];
    const allMsg = yield Promise.all(data.base64.map((att, index) => __awaiter(this, void 0, void 0, function* () {
        const fileName = data.email + "-" + data.fileNames[index] + "-" + new Date();
        const file = myBucket.file(fileName);
        var buff = Buffer.from(att, 'binary').toString('utf-8');
        const stream = file.createWriteStream({
            metadata: {
                contentType: 'Application/octet-stream',
            },
            public: true,
        });
        yield stream.on('error', (err) => {
            throw err;
        });
        yield stream.end(new Buffer(buff, 'base64'));
        cloudUrl.push(`https://storage.googleapis.com/${BUCKET_NAME}/${fileName}`);
    })));
    query['url'] = cloudUrl;
    return query;
});
//# sourceMappingURL=cloud.js.map