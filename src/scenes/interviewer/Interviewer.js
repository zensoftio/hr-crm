import React, { Component } from 'react';
import Navigation from '../general/Navigation';
import { BrowserRouter, Route, Switch } from 'react-router-dom';
import ListInterview from './ListInterview';
import CandidateGrade from './CandidateGrade';

export default class AdminScene extends Component {
    render() {
			const interviewerNav = [
				{
					name: 'Список Интервью',
        	path: '/list_interview'
				}
			];
         return(
          <BrowserRouter>
						<div className="container">
							<Navigation menuItems={interviewerNav}/>
							<div className="content">
								<Switch>
									<Route path="/list_interview" component={ListInterview} exact/>
									<Route path="/candidate_grade" component={CandidateGrade} />
								</Switch>
							</div>
						</div>
					</BrowserRouter>
					
			  );
    }
}