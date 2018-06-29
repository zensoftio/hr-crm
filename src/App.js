import './index.css';
import React from 'react';
//import { observer } from 'mobx-react';
import {BrowserRouter, Route, Switch} from 'react-router-dom';
import Home from './scenes/general/Home';
import OpenedPositions from './scenes/head/OpenedPositions';
import Error from './scenes/general/Error';
import Navigation from './scenes/general/Navigation';
import CreatePosition from './scenes/head/CreatePosition';
import Archive from './scenes/head/Archive';
import EditPositions from './scenes/head/EditPositions';
import Statistics from './scenes/general/Statistics';
import CreateVacancy from './scenes/hr/CreateVacancy';
import ListCandidates from "./scenes/hr/ListCandidates";
import ListInterns from "./scenes/hr/ListIntern";
import ListReserves from "./scenes/hr/Reserve";
import ListInterviews from "./scenes/hr/ListInterviews";
import ListNotifications from "./scenes/hr/Notifications";

class App extends React.Component {
    render() {
        return (
            <BrowserRouter>
                <div className="container">

                    <Navigation/>

                    <div className="content">
                        <Switch>
                            <Route path="/" component={Home} exact/>
                            <Route path="/hr/create_vacancy" component={CreateVacancy} exact/>
                            <Route path="/create_position" component={CreatePosition}/>
                            <Route path="/opened_positions" component={OpenedPositions}/>
                            <Route path="/archive" component={Archive}/>
														<Route path="/edit_positions" component={EditPositions}/>
                            <Route path="/statistics" component={Statistics}/>

                            <Route path="/hr/list_candidates" component={ListCandidates}/>
                            <Route path="/hr/list_interns" component={ListInterns}/>
                            <Route path="/hr/list_reserves" component={ListReserves}/>
                            <Route path="/hr/list_interviews" component={ListInterviews}/>
                            <Route path="/hr/list_notifications" component={ListNotifications}/>
                            <Route component={Error}/>
                        </Switch>
                    </div>
                </div>
            </BrowserRouter>
        );
    }
}
export default App;