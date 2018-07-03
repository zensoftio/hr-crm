import React, {Component} from 'react';
import EditInterviewContainer from "../../components/containers/hr/EditInterviewContainer";

class EditInterview extends Component {
    render() {
        const {classes} = this.props;
        return (

            <div>
                <h1>Edit interview</h1>

                <EditInterviewContainer/>
            </div>

        );
    }
}

export default EditInterview;