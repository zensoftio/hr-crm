import React, {Component} from 'react';
import Header from '../general/Header';
import ListOfInterviews from "../../components/containers/hr/ListOfInterviewsContainer";

class ListInterviews extends Component {
    render() {
        return (
            <div>
                <Header title="Интервью"/>

                <ListOfInterviews/>
            </div>
        )
    }
}

export default ListInterviews;

