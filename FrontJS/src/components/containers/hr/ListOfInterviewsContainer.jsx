import React, { Component } from "react";
import TableList from "../../ui/Table";
import { FetchDataAPI } from "../../../services/FetchDataAPI";
import { INTERVIEWS_URL } from "../../../utils/urls";
import makeLinked from '../../../utils/MakeLinked';
import getLink from '../../../utils/GetLink';

const header = ['№', 'Ф.И.О', 'ПОЗИЦИЯ', 'СТАТУС', 'ДАТА', 'РЕДАКТИРОВАТЬ'];

class ListOfInterviews extends Component {

    constructor(props) {
        super(props);
        this.state = {
            data: [],
        }
    }

    parseDate = (value) => {
        const parsedDate = new Date(value);

        return parsedDate.toLocaleString('en-US')
    }

    componentWillMount = () => {
        FetchDataAPI(INTERVIEWS_URL)
            .then(response => response.results.map(
                item => (
                    {
                        id: item.id,
                        full_name: item.candidate.first_name + ' ' + item.candidate.last_name,
                        date: item.date,
                        status: item.status,
                        department: item.candidate.position.name,
                    }
                )
            ))
            .then(data => this.setState({data}))
    };

    render() {
        const { data } = this.state;

        const interviews = data.map(
            item => [
                item.full_name,
                item.department,
                item.status,
                this.parseDate(item.date),
                makeLinked('Открыть', getLink("edit_interview", item.id))
            ]
        );

        return (<TableList header={header} data={interviews}/>);
    }
}


export default ListOfInterviews;
