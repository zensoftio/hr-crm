import React, { Component } from "react";
import TableList from "../../ui/Table";
import { Button } from '@material-ui/core';
import AddIcon from '@material-ui/icons/Add';
import { REQUESTS_URL } from '../../../utils/urls'
import { FetchDataAPI } from "../../../services/FetchDataAPI";
// import { Route, Link } from 'react-router-dom';
// import CreateVacancy from '../../containers/hr/CreateVacancyContainer';

const header = ['№', 'ЗАГОЛОВОК', 'ДАТА', 'КОЛ-ВО','СТАТУС', 'СОЗДАТЬ'];

class ListOfPositions extends Component {

    constructor(props){
        super(props);
        this.state = {
            data: [],
        };
    }

    dateConvert = (date) => {
        return new Date(date).toLocaleString('ru', {
            year: 'numeric',
            month: 'long',
            day: 'numeric'
        });
    };

    initStatus = (status) => {
        if(status === 'NOT_REVIEWED'){
            return "Не рассмотрено"
        } else if(status === 'APPROVED'){
            return "Утверждено"
        } else if(status === 'DECLINED'){
            return  "Отклонено"
        } else {
            return null
        }
    };

    componentDidMount() {
        const fetched = FetchDataAPI(REQUESTS_URL);
        fetched.then(response => response.results.map(item => (
            {
                request_id: item.id,
                title: item.position.name,
                created: item.created,
                quantity: item.count,
                status: item.status
            })
        )).then(data => this.setState({
            data
        }))
    }

    render() {

        const { data } = this.state;

        const array = data.map(item => {
            return [
                item.title,
                this.dateConvert(item.created),
                item.quantity,
                this.initStatus(item.status),
                // <Button variant="fab" color="primary" mini aria-label="add" type="button" onClick={() => handleSubmit(item.request_id)}>
                //     <AddIcon />
                // </Button>
                <button type="button" onClick={handleSubmit(item.request_id)}>Create</button>
            ]
        })
        

        return ( 
            <div>
 
                <TableList header={header} data={array}/>
            </div>
             
        )
    }
}

const handleSubmit = (id) => {
    // return(
    //     <div>Request id is: { id }</div>
    // )
    console.log(id)
}

export default ListOfPositions;