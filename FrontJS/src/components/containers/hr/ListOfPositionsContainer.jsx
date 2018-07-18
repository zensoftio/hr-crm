import React, { Component } from "react";
import TableList from "../../ui/Table";
import { REQUESTS_URL } from '../../../utils/urls'
import { FetchDataAPI } from "../../../services/FetchDataAPI";
import DateConvert from '../../../utils/DateConvert';
import makeLinked from '../../../utils/MakeLinked';
import getLink from '../../../utils/GetLink';

const header = ['№', 'ЗАГОЛОВОК', 'ДАТА', 'КОЛ-ВО','СТАТУС', 'СОЗДАТЬ'];

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

        const fetched = FetchDataAPI(REQUESTS_URL + '?status=APPROVED&size=1000');
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
                DateConvert(item.created),
                item.quantity,
                this.initStatus(item.status),
                makeLinked('Создать', getLink('create_vacancy', item.request_id))
            ]
        })

        return ( 
            <div>
                <TableList header={header} data={array}/>
            </div>
        )
    }
}

export default ListOfPositions;