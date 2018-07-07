import axios from 'axios';

export function PostDataAPI(url, data) {
    
    axios.post(url, {
        method: "POST",
        body: data
    }).then(function (response) {
        console.log(response);
    }).catch(function (error) {
        console.log(error);
    });

}