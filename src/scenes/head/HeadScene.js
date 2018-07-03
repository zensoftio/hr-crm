import React, { Component } from 'react';
import { BrowserRouter, Route, Switch } from 'react-router-dom';
import OpenedPositions from './OpenedPositions';
import Error from '../general/Error';
import Navigation from '../../scenes/general/Navigation';
import CreatePosition from '../../scenes/head/CreatePosition';
import Archive from '../../scenes/head/Archive';
import Statistics from '../../scenes/general/Statistics';
import SpecifyTheRoute from '../../utils/Route';
import EditPositions from './EditPositions';
import '../../index.css';

export default class HeadScene extends Component {
    render() {
        return( 
            <BrowserRouter>
                <div className="container">


                	
                 <Navigation menuItems={HeadNav}/>

                    <div className="content">
                        <Switch>
                            <Route path="/" component={OpenedPositions} exact/>
                            <SpecifyTheRoute route={HeadNav} />
                            <Route component={Error}/>
                        </Switch>
                    </div>

                </div>
            </BrowserRouter>
        );
    }
}

const HeadNav = [
    {
        name: 'Создать позицию',
        path: '/create_position',
        component: CreatePosition
    },
    {
        name: 'Открытые Позиции',
        path: '/opened_positions',
        component: OpenedPositions
    },
    {
        name: 'Архив Позиций',
        path: '/archive',
        component: Archive
    },
    {
        name: 'Статистика',
        path: '/statistics',
        component: Statistics
		},
		{			
			path: '/edit_positions',
			component: EditPositions
	}
]