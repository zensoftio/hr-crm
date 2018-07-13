const Promise = require('bluebird');
const GoogleCloudStorage = Promise.promisifyAll(require('@google-cloud/storage'));
const BUCKET_NAME = 'candidate-cvs'
const stream = require('stream');
const storage = GoogleCloudStorage({
  projectId: 'hr-zensoft',
  keyFilename: 'service-account.json'
});
const myBucket = storage.bucket(BUCKET_NAME)

exports.uploadToStrage = async (data) => {
  var query = {
    'email': data.email
  };
  query['url'];
  var cloudUrl = [];
  const allMsg = await Promise.all(data.base64.map(async (att,index) => {
    const fileName = data.email + "-" + data.fileNames[index] + "-" + new Date();
    const file = myBucket.file(fileName);
    var buff = Buffer.from(att, 'binary').toString('utf-8');
    const stream = file.createWriteStream({
      metadata: {
        contentType: 'Application/octet-stream',
      },
      public: true,
    });
    await stream.on('error', (err) => {
      throw err;
    });
    await stream.end(new Buffer(buff, 'base64'));
    cloudUrl.push(`https://storage.googleapis.com/${BUCKET_NAME}/${fileName}`)
  }));
  query['url'] = cloudUrl;
  return query;
}
