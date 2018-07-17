import axios from 'axios';

export function PutDataAPI(url, data) {

    axios({
        url: url,
        method: "PUT",
        data: data
    }).then(function (response) {
        console.log(response.status);
        alert("Действие успешно совершено!")
    }).catch(function (error) {
        alert("Возникли Ошибки", error)
        console.log(error);
    });

}
