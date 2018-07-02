import React, { Component } from 'react';
import Navigation from '../general/Navigation';
import RequesList from './RequestList';
import Statistics from '../general/Statistics';
import Error from '../general/Error';
import { BrowserRouter, Route, Switch } from 'react-router-dom';
import EditRequest from './EditRequest';

export default class AdminScene extends Component {
    render() {
			const pmNav = [
				{
					name: 'Список Запросов',
					path: '/request_list'
				},
				{
					name: 'Статистика',
					path: '/statistics'
				},
				{					
					path: '/edit_request'
				}
			];
        return(
          <BrowserRouter>
						<div className="container">
							<Navigation menuItems={pmNav} />

							<div className="content">
								<Switch>								
									
									<Route path="/request_list" component={RequesList}/>
									<Route path="/statistics" component={Statistics}/>
									<Route path="/edit_request" component={EditRequest}/>
									<Route component={Error} />
								</Switch>
							</div>
						</div>
					</BrowserRouter>
        );
    }
}