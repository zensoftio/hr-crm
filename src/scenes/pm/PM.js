import React, { Component } from 'react';
import { BrowserRouter, Route, Switch } from 'react-router-dom';
import Navigation from '../general/Navigation';
import RequesList from './RequestList';
import Statistics from '../general/Statistics';
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
								</Switch>
							</div>
						</div>
					</BrowserRouter>
        );
    }
}