import React, { Component } from "react";
import TableList from "../../ui/Table";
import { Button, CircularProgress } from '@material-ui/core';
import AddIcon from '@material-ui/icons/Add';
import { REQUESTS_URL } from '../../../utils/urls'
import { FetchDataAPI } from "../../../services/FetchDataAPI";
import DateConvert from "../../../utils/DateConvert";

const header = ['№', 'ЗАГОЛОВОК', 'ДАТА', 'КОЛ-ВО','СТАТУС', 'СОЗДАТЬ'];

const button = [
    <Button variant="fab" mini color="primary" aria-label="add">
        <AddIcon />
    </Button>
];

class ListOfPositions extends Component {

    constructor(props){
        super(props);
        this.state = {
            data: [],
        };
    }

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

    addPreloader = () => {
        return  <CircularProgress />
    };

    render() {

        const { data } = this.state;

        const array = data.map(item => {
            return [
                item.title,
                DateConvert(item.created),
                item.quantity,
                this.initStatus(item.status),
                button]
        });

        return (
            <TableList header={header} data={array}/>
        )
    }
}

export default ListOfPositions;