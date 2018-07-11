import axios from 'axios';

export function FetchDataAPI(url) {
 
    return axios(url, {
        method: "GET"
    }).then(response => {
        return response.data;
    }).catch(error => {
        alert("Bad request", error);
    })

}