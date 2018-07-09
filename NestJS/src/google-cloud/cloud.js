const Promise = require('bluebird');
const GoogleCloudStorage = Promise.promisifyAll(require('@google-cloud/storage'));
// const exports = module.exports = {};
const BUCKET_NAME = 'candidate-cvs'
const stream = require('stream');
const storage = GoogleCloudStorage({
  projectId: 'hr-zensoft',
  keyFilename: '/home/belek/Desktop/nestJs/hr-crm/NestJS/service-account.json'
})
const myBucket = storage.bucket(BUCKET_NAME)


exports.uploadToStrage = async (fileInBase64,email) => {
    const file = bucket.file(email);
    var buff = Buffer.from(fileInBase64, 'binary').toString('utf-8');
    const stream = file.createWriteStream({
      metadata: {
        contentType: 'Application/octet-stream',
      },
      public: true,
    });
    await stream.on('error', (err) => {
      throw err;
    });
    await stream.on('finish', (finish) => {
      console.log(finish);
    });
    await stream.end(new Buffer(buff, 'base64'));

    return `https://storage.googleapis.com/${BUCKET_NAME}/${email}`
}

isExist = async (fileName) => {
  const file = myBucket.file(`${fileName}`);
  const  checkForExist = await file.existsAsync();
  if (checkForExist)return false;
  return true;
}
