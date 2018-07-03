import React, {Component} from 'react';
import PositionListContainer from '../../components/containers/hr/ListOfPositionsContainer';
import Header from '../../scenes/general/Header';

class PositionList extends Component {
    render() {
        return (
            <div>
                <Header title="Список заявок на подбор персонала"/>
                <PositionListContainer/>
            </div>
        )
    }
}

export default PositionList;