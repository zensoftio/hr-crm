import React, { Component } from "react";
import TableList from "../../ui/Table";
import { Link } from 'react-router-dom';
import { FetchDataAPI } from "../../../services/FetchDataAPI";
import { CANDIDATES_URL } from "../../../utils/urls";
import DateConvert from '../../../utils/DateConvert';

const header = ['№', 'Ф.И.О', 'ЯЗЫК', 'СТАТУС', 'ДАТА', 'ПРОФИЛЬ'];

const CANDIDATE_STATUS = {
    'NOT_REVIEWED': 'Не рассмотрено',
    'REVIEWED': 'Рассмотрено',
    'SATISFYING': 'Подходит',
    'NOT_SATISFYING': 'Не подходит',
    'TEST_SENT': 'Отправлен тест',
    'INVITED_TO_INTERVIEW': 'Приглашен на интервью',
    'INTERVIEWS_CONDUCTED': 'Интервью проведено',
    'CURRENT_EMPLOYEE': 'Штат',
    'IN_RESERVE': 'Резерв',
    'INTERN': 'Стажёр',
    'FAILED_INTERVIEW': 'Не прошел интервью',
};

class ListOfCandidates extends Component {

    constructor(props) {
        super(props);
        this.state = {
            data: [],
        }
    }

    makeLinked = (element, link) => (
        <Link to={link}>{element} </Link>
    );

    getStatus = (status) => {
        return CANDIDATE_STATUS[status]
    };

    getLink = (id) => {
        return CANDIDATES_URL + '/' + id;
    };

    componentWillMount = () => {
        FetchDataAPI(CANDIDATES_URL)
            .then(response => response.results.map(
                item => (
                    {
                        id: item.id,
                        full_name: item.first_name + ' ' + item.last_name,
                        created_at: item.created,
                        status: item.status,
                        language: item.position.department.name,
                    }
                )
            ))
            .then(data => this.setState({data}))
    };

    render() {
        const { data } = this.state;
        const candidates = data.map(
            item => [
                item.full_name,
                item.language,
                this.getStatus(item.status),
                DateConvert(item.created_at),
                this.makeLinked('Открыть', this.getLink(item.id))
            ]
        );

        return (<TableList header={header} data={candidates}/>);
    }
}


export default ListOfCandidates;
