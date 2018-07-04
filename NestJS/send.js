#!/usr/bin/env node

var amqp = require("amqp-ts");

var connection = new amqp.Connection("amqp://guest:guest@localhost:5672");
//var exchange = connection.declareExchange("ExchangeName", 'direct');
var queue = connection.declareQueue("event", {durable: true});

function sysLog(data) {
  console.log('\n');
  console.log(data);
  console.log('\n');
}
var jsonObj =   {
  "title": "create",
    "body": {
        "id": 11,
        "id_event": "e3atn6qgk1m8rad1jjrslnm1og",
        "begin_time": "2018-07-0111:31:00+06:00",
        "end_time": "2018-07-01T19:31:00+06:00",
        "email": ["shisyr2106@gmail.com", "shisyr96@gmail.com"],
        "description": "Description3",
        "location": "Ahunbaeva3",
        "summary": "Summary3"
    }
}

function main() {
  const res = queue.rpc(JSON.stringify(jsonObj), 'black').then((result) => {
      let json = JSON.parse(result.getContent());
      console.log(json);
  });
   setTimeout(function() {
     connection.close();
   }, 5000);

}

main();
