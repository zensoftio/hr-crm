import axios from 'axios';

export function PutDataAPI(url, data) {

    axios({
        url: url,
        method: "PATCH",
        data: data
    }).then(function (response) {
        console.log(response);
    }).catch(function (error) {
        console.log(error);
    });

}