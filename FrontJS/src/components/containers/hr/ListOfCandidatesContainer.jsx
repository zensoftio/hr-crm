import React, { Component } from "react";
import TableList from "../../ui/Table";
import { FetchDataAPI } from "../../../services/FetchDataAPI";
import { CANDIDATES_URL } from "../../../utils/urls";
import DateConvert from '../../../utils/DateConvert';
import makeLinked from '../../../utils/MakeLinked';
import getLink from '../../../utils/GetLink';
import getStatus from '../../../utils/GetStatus';

const header = ['№', 'Ф.И.О', 'ЯЗЫК', 'СТАТУС', 'ДАТА', 'ПРОФИЛЬ'];

class ListOfCandidates extends Component {

    constructor(props) {
        super(props);
        this.state = {
            data: [],
        }
    }

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
                getStatus(item.status),
                DateConvert(item.created_at),
                makeLinked('Открыть', getLink("profile", item.id))
            ]
        );

        return (<TableList header={header} data={candidates}/>);
    }
}


export default ListOfCandidates;
