import React, { Component } from "react";
import TableList from "../../ui/Table";
import { CANDIDATES_URL } from "../../../utils/urls";
import { FetchDataAPI } from "../../../services/FetchDataAPI";
import makeLinked from '../../../utils/MakeLinked';
import getLink from '../../../utils/GetLink';

const header = ['№', 'Ф.И.О', 'ДЕПАРТАМЕНТ', 'ПРОФИЛЬ'];

const QUERY_URL = CANDIDATES_URL + '?status=IN_RESERVE';

class ListOfReserves extends Component {

    constructor(props) {
        super(props);
        this.state = {
            data: [],
        }
    }

    componentWillMount = () => {
        FetchDataAPI(QUERY_URL)
            .then(response => response.results.map(
                item => (
                    {
                        id: item.id,
                        full_name: item.first_name + ' ' + item.last_name,
                        language: item.position.department.name,
                    }
                )
            ))
            .then(data => this.setState({data}))
    };

    render() {
        const { data } = this.state;
        const reservedCandidates = data.map(
            item => [
                item.full_name,
                item.language,
                makeLinked('Открыть', getLink("profile", item.id))
            ]
        );

        return (<TableList header={header} data={reservedCandidates}/>);
    }
}

export default ListOfReserves;