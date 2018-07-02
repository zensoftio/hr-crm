import React, { Component } from 'react';
import { BrowserRouter, Route, Switch } from 'react-router-dom';
import OpenedPositions from './OpenedPositions';
import Error from '../general/Error';
<<<<<<< HEAD
import Navigation from '../general/Navigation';
import CreatePosition from './CreatePosition';
import Archive from './Archive';
import Statistics from '../general/Statistics';
import EditPositions from './EditPositions';
=======
import Navigation from '../../scenes/general/Navigation';
import CreatePosition from '../../scenes/head/CreatePosition';
import Archive from '../../scenes/head/Archive';
import Statistics from '../../scenes/general/Statistics';
import SpecifyTheRoute from '../../utils/Route';
>>>>>>> c9cb7a6ddf31a7b873ed0bdbb432e4620385f04d
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
<<<<<<< HEAD
                        <Route path="/create_position" component={CreatePosition}/>   
                        <Route path="/opened_positions" component={OpenedPositions}/>
                        <Route path="/archive" component={Archive}/>
                        <Route path="/statistics" component={Statistics}/>
												<Route path="/edit_positions" component={EditPositions}/>
=======
                        <SpecifyTheRoute route={HeadNav} />
>>>>>>> c9cb7a6ddf31a7b873ed0bdbb432e4620385f04d
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
    }
]