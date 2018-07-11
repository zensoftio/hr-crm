import React, {Component} from 'react';
import Header from '../general/Header';
import ListOfNotifications from "../../components/containers/hr/ListOfNotificationsContainer";

class ListNotifications extends Component {
    render() {
        return (
            <div>
                <Header title="Уведомление"/>

                <ListOfNotifications/>
            </div>
        )
    }
}

export default ListNotifications;

