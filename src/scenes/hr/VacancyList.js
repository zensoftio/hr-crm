import React, { Component } from 'react';
import Navigation from '../general/Navigation';
import './index.css';
import { BrowserRouter, Route, Switch } from 'react-router-dom';

import ListPositions from './ListPositions';
import CreateVacancy from './CreateVacancy';
import ListCandidates from './ListCandidates';
import Reserve from './Reserve';
import ListIntern from './ListIntern';
import Statistics from '../general/Statistics';
import InterviewList from './InterviewList';
import Notifications from './Notifications';





class CreateVacancy extends Component {
    render() {
        return(
            <BrowserRouter>
            <div className="container">
    
              <Navigation />
    
              <div className="content">
                <Switch>
                  <Route path="hr/list_of_positions" component={ListPositions}/>   
                  <Route path="hr/create_vacancy" component={CreateVacancy}/>
                  <Route path="hr/" component={VacancyList}/>
                  <Route path="hr/list_of_candidates" component={ListCandidates}/>
                  <Route path="hr/reserve" component={Reserve}/>
                  <Route path="hr/list_of_interns" component={ListIntern}/>
                  <Route path="hr/statistics" component={Statistics}/>
                  <Route path="hr/list_of_interviews" component={InterviewList}/>
                  <Route path="hr/notifications" component={Notifications}/>   
                  <Route path="hr/logout" component={Logout}/>
                  <Route component={Error}/>
                </Switch>
              </div>
            </div>
          </BrowserRouter>
        )
    }
}

export default CreateVacancy;