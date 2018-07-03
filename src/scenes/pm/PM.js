import React, { Component } from 'react';
import Navigation from '../general/Navigation';
import RequesList from './RequestList';
import Statistics from '../general/Statistics';
import Error from '../general/Error';
import { BrowserRouter, Route, Switch } from 'react-router-dom';
import EditRequest from './EditRequest';
import SpecifyTheRoute from '../../utils/Route';


export default class AdminScene extends Component {
    render() {
			const pmNav = [
				{
					name: 'Список Запросов',
					path: '/request_list',
					component: RequesList
				},
				{
					name: 'Статистика',
					path: '/statistics',
					component: Statistics
				},
				{		
					path: '/edit_request',
					component: EditRequest
				}
			];
        return(
          <BrowserRouter>
						<div className="container">
							<Navigation menuItems={pmNav} />

							<div className="content">
								<Switch>
									<SpecifyTheRoute route={pmNav} />
									<Route component={Error} />
								</Switch>
							</div>
						</div>
					</BrowserRouter>
        );
    }
}