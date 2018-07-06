var amqp = require('amqplib/callback_api');

let channel;
let done = false;
if(channel === undefined){
  amqp.connect('amqp://guest:guest@localhost:5672', function(err, conn) {
    conn.createChannel(function(err, ch) {
      channel = ch;
      done = true;
    });
  });
  require('deasync').loopWhile(function(){return !done});
}

export default channel;
