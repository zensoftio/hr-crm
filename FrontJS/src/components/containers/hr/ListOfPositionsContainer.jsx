import React, { Component } from "react";
import TableList from "../../ui/Table";
import { Button, CircularProgress} from '@material-ui/core';
import AddIcon from '@material-ui/icons/Add';

const header = ['№', 'ЗАГОЛОВОК', 'ДАТА', 'КОЛ-ВО','СТАТУС', 'СОЗДАТЬ'];

const button = [
    <Button variant="fab" mini color="primary" aria-label="add">
        <AddIcon />
    </Button>
];

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
        if(status === 1){
            return "Не рассмотрено"
        } else if(status === 2){
            return "Утверждено"
        } else if(status === 3){
            return  "Отклонено"            
        } else {
            return null
        }
    }

    componentDidMount() {
        const URL_REQUESTS = "http://159.65.153.5/api/v1/requests/";
   
        fetch(URL_REQUESTS, {
            method: "GET"
        }).then(res => res.json())
        .then(json => json.results.map(item => (
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
    
    addPreloader = () => {
        return  <CircularProgress />
    }

    render() {

        const { data } = this.state;

        const array = data.map(item => {
            return [
                item.title, 
                this.dateConvert(item.created), 
                item.quantity,
                this.initStatus(item.status),
                button]
        })
        
        return ( 
            <TableList header={header} data={array}/> 
        )
    }
}

export default ListOfPositions;