import axios from 'axios';

export function DeleteDataAPI(url) {
    
    axios({
        url: url,
        method: "DELETE",
    }).then(function (response) {
        console.log(response);
    }).catch(function (error) {
        console.log(error);
    });

}