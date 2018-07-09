const Promise = require('bluebird');
const GoogleCloudStorage = Promise.promisifyAll(require('@google-cloud/storage'));
const BUCKET_NAME = 'candidate-cvs'
const stream = require('stream');
const storage = GoogleCloudStorage({
  projectId: 'hr-zensoft',
  keyFilename: '/home/belek/Desktop/nestJs/hr-crm/NestJS/service-account.json'
});
const myBucket = storage.bucket(BUCKET_NAME)

exports.uploadToStrage = async (data) => {
  console.log(data.email);
  console.log(data.base64.length);
  var query = {
    'email': data.email
  };
  query['url'];
  var cloudUrl = [];
  const allMsg = await Promise.all(data.base64.map(async (att,index) => {
    console.log("IN");
    const fileName = data.email + "-" + data.fileNames[index]
    const file = myBucket.file(fileName);
    var buff = Buffer.from(att, 'binary').toString('utf-8');
    const stream = file.createWriteStream({
      metadata: {
        contentType: 'Application/octet-stream',
      },
      public: true,
    });
    console.log("BEFORE ERR");
    await stream.on('error', (err) => {
      console.log(err);
      // throw err;
    });
    console.log("END ERR");
    console.log("BEFORE FINISH");
    await stream.on('finish', (finish) => {
      console.log(finish);
    });
    console.log("END FINISH");

    console.log("BEFORE UPLOAD");
    await stream.end(new Buffer(buff, 'base64'));
    console.log("AFTER UPLOAD");
    cloudUrl.push(`https://storage.googleapis.com/${BUCKET_NAME}/${fileName}`)
  }));
  query['url'] = cloudUrl;
  console.log("BEFORE RETURN");
  return query;
}

isExist = async (fileName) => {
  const file = myBucket.file(`${fileName}`);
  const  checkForExist = await file.existsAsync();
  if (checkForExist)return false;
  return true;
}
