import React, {Component} from 'react';
import EditInterviewContainer from "../../components/containers/hr/EditInterviewContainer";
import Header from "../general/Header";

class EditInterview extends Component {
    render() {
        return (
            <div>
                <Header title="Изменить интервью"/>
                <EditInterviewContainer/>
            </div>

        );
    }
}

export default EditInterview;