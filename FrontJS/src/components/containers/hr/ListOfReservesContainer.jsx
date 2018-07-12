import React, { Component } from "react";
import TableList from "../../ui/Table";
import { CANDIDATES_URL } from "../../../utils/urls";
import { Link } from "react-router-dom";
import { FetchDataAPI } from "../../../services/FetchDataAPI";


const header = ['№', 'Ф.И.О', 'ДЕПАРТАМЕНТ', 'ПРОФИЛЬ'];

const QUERY_URL = CANDIDATES_URL + '?status=IN_RESERVE';


class ListOfReserves extends Component {

    constructor(props) {
        super(props);
        this.state = {
            data: [],
        }
    }

    makeLinked = (element, link) => (
        <Link to={link}>{element} </Link>
    );

    getLink = (id) => {
        return CANDIDATES_URL + '/' + id;
    };


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
                this.makeLinked('Открыть', this.getLink(item.id))
            ]
        );

        return (<TableList header={header} data={reservedCandidates}/>);
    }
}

export default ListOfReserves;