import React, {Component} from 'react';
import Header from '../general/Header';
import ListOfReserves from "../../components/containers/hr/ListOfReservesContainer";

class ListReserves extends Component {
    render() {
        return (
            <div>
                <Header title="Резерв"/>

                <ListOfReserves/>
            </div>
        )
    }
}

export default ListReserves;

