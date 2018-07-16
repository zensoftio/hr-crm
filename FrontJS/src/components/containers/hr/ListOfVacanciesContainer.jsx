import React, { Component } from "react";
import TableList from "../../ui/Table";
import { FetchDataAPI } from "../../../services/FetchDataAPI";
import { VACANCIES_URL } from '../../../utils/urls'
import makeLinked from '../../../utils/MakeLinked';
import getLink from '../../../utils/GetLink';
import DateConvert from '../../../utils/DateConvert';

const header = ['№', 'НАЗВАНИЕ', 'ДАТА СОЗДАНИЯ', 'ПОСЛЕДНЯЯ ПУБЛ.', 'ДЕЙСТВИЕ'];

class ListOfVacancies extends Component {

    constructor(props) {
        super(props);
        this.state = {
            data: [],
        }
    }

    componentDidMount() {
        FetchDataAPI(VACANCIES_URL + '?status=1')
            .then(res => res.results.map(
                vacancy => (
                    {
                        vacancy_id: vacancy.id,
                        title: vacancy.name,
                        created: vacancy.created,
                        last_published: vacancy.last_published
                    }
                )
            )).then(data => this.setState({ data }))
    }

    render() {
        console.log(VACANCIES_URL)
        const { data } = this.state;
        const openedVacancies = data.map(
            item => [
                item.title,
                DateConvert(item.created),
                DateConvert(item.last_published),
                makeLinked('Открыть', getLink("vacancy", item.vacancy_id))
            ]
        );
    
        return (<TableList header={header} data={openedVacancies}/>);
    } 
}

export default ListOfVacancies;