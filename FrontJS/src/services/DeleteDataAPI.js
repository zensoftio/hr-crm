import axios from 'axios';

export function DeleteDataAPI(url, id) {
    
    axios({
        url: url,
				method: "DELETE",
				data: {
					id
				}
    }).then(function (response) {
        console.log(response);
    }).catch(function (error) {
        console.log(error);
    });

}