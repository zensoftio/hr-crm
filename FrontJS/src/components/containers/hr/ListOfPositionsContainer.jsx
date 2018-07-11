import React, { Component } from "react";
import TableList from "../../ui/Table";
import { Button } from '@material-ui/core';
import AddIcon from '@material-ui/icons/Add';
import { REQUESTS_URL } from '../../../utils/urls'
import { FetchDataAPI } from "../../../services/FetchDataAPI";
import { Route, Link } from 'react-router-dom';
import CreateVacancy from '../../containers/hr/CreateVacancyContainer';

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

    addPath = (value) => {
        <Route path="dgdfgfd" />
    }

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
                <Link to="jen">Создать</Link>
                // <Route path="create_vacancy/:id" render={(props) => <CreateVacancy {...props} />}>
                //     <Link to={`create_vacancy/${item.requests_id}`}>Создать</Link>
                // </Route>
            ]
        })
        

        return ( 
            <div>
                <Route path="list_of_positions/:id" component={User}/>
                <TableList header={header} data={array}/>
            </div>
             
        )
    }
}

const User = ({ match }) => {
    return <h1>ID: {match.params.id}</h1>
}

export default ListOfPositions;