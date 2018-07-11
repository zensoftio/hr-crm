const Nexmo = require('nexmo');
const nexmo = new Nexmo({
  apiKey: '11bafd6d',
  apiSecret: 'gbI3hMGS3hemoe9N'
});

module.exports = {
  send: async function(json, title){
    let results = [];
    if(title === 'delete'){
      json.description = 'We decline interview!'
      json.location = '';
    }
    for(let i = 0;i < json.phone.length;i++){
      let data = `Dear ${json.email[i]}, ${json.description}. Place: ${json.location}. Time: ${json.begin_time}`;
      const result = await run(json.phone[i], data);
      (result) ? results.push(result) : result;
    }
    (results.length == json.phone.length) ? true : false;
  }
}

async function run(phone, data){
  return new Promise((resolve, reject) => {
    nexmo.message.sendSms(
      'NEXMO', phone, data,
      (err, responseData) => {
        if (err) {
          reject(false);
        } else {
          resolve(true)
        }
      }
    );
  })
}
