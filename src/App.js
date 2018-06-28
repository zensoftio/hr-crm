import './index.css';
import React from 'react';
//import { observer } from 'mobx-react';
import { BrowserRouter, Route, Switch } from 'react-router-dom';
import Home from './scenes/general/Home';
import OpenedPositions from './scenes/head/OpenedPositions';
import Error from './scenes/head/Error';
import Navigation from './scenes/general/Navigation';
import CreatePosition from './scenes/head/CreatePosition';
import Archive from './scenes/head/Archive';
import Statistics from './scenes/general/Statistics';
import CreateVacancy from './scenes/hr/CreateVacancy';
import ListCandidates from "./scenes/hr/ListCandidates";

class App extends React.Component {  
  render() {
    return (
      <BrowserRouter>
        <div className="container">

          <Navigation />

          <div className="content">
            <Switch>
              <Route path="/" component={Home} exact/>
              <Route path="/hr/create_vacancy" component={CreateVacancy} exact/>
              <Route path="/create_position" component={CreatePosition}/>   
              <Route path="/opened_positions" component={OpenedPositions}/>
              <Route path="/archive" component={Archive}/>
              <Route path="/statistics" component={Statistics}/>

                <Route path="/hr/list_candidates" component={ListCandidates}/>
              <Route component={Error}/>
            </Switch>
          </div>
        </div>
      </BrowserRouter>
    );
  }
};

export default App;