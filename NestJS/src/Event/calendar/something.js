var google = require('./google.calendar');

var json = {
    "description": "list"
    // "content":{
    //   // "id": "1",
    //   // "id_event": "2ewllwdsl",
    //   // "date": "29-06-2018",
    //   // "begin_time": "2018-06-29T11:31:00+06:00",
    //   // "end_time": "2018-06-29T19:31:00+06:00",
    //   // "email": ["shisyr2106@gmail.com", "shisyr96@gmail.com"],
    //   // "description": "Description",
    //   // "location": "Sovetskaya",
    //   // "summary": "Interview"
    // }
 };
 // if(json.content.id_event){
 //   console.log("sdf");
 //  }
google.run(json, function(response) {

  console.log(response);
})
