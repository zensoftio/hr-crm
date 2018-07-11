import React, { Component } from "react";
import TableList from "../../ui/Table";
import { Button } from '@material-ui/core';
import AddIcon from '@material-ui/icons/Add';
import CreateVacancy from '../../../scenes/hr/CreateVacancy';
import { Link, Route } from 'react-router-dom';
import { FetchDataAPI } from "../../../services/FetchDataAPI";

const header = ['№', 'ЗАГОЛОВОК', 'ДАТА', 'КОЛ-ВО','СТАТУС', 'СОЗДАТЬ'];

class ListOfPositions extends Component {
    
    constructor(props){
        super(props)
        this.state = {
            data: [],
        };
    }

    dateConvert = (date) => {
        const newDate = new Date(date).toLocaleString('ru', {
            year: 'numeric',
            month: 'long',
            day: 'numeric'
        });
        return newDate;
    }

    initStatus = (status) => {
        if(status === "NOT_REVIEWED"){
            return "Не рассмотрено"
        } else if(status === "APPROVED"){
            return "Утверждено"
        } else if(status === "DECLINED"){
            return  "Отклонено"            
        } else {
            return null
        }
    }

    addPath = (value) => {
        <Route path="dgdfgfd" />
    }

    componentDidMount() {
        const URL_REQUESTS = "http://159.65.153.5/api/v1/requests";
   
        const fetched = FetchDataAPI(URL_REQUESTS);
        fetched.then(res => res.results.map(item => (
          {
            requests_id: item.id,
            title: item.position.name,
            created: item.created,
            quantity: item.count,
            status: item.status
          })
        )).then(data => this.setState({
            data
        })).catch(error => {
            alert("You send wrong request", error);
        })
   
    }

    render() {

        const { data } = this.state;

        const array = data.map(item => {
            return [
                item.title, 
                this.dateConvert(item.created), 
                item.quantity,
                this.initStatus(item.status),
                // <Route component={<CreateVacancy data={item.requests_id} />}>
                    <Link to={`create_vacancy/${item.requests_id}`}>Создать</Link>
                // </Route>
            ]
        })
        

        return ( 
            <TableList header={header} data={array}/> 
        )
    }
}

export default ListOfPositions;