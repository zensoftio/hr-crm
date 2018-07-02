import React, { Component } from 'react';
import { BrowserRouter, Route, Switch } from 'react-router-dom';
import OpenedPositions from './OpenedPositions';
import Error from '../general/Error';
import Navigation from '../general/Navigation';
import CreatePosition from './CreatePosition';
import Archive from './Archive';
import Statistics from '../general/Statistics';
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
                        <Route path="/create_position" component={CreatePosition}/>   
                        <Route path="/opened_positions" component={OpenedPositions}/>
                        <Route path="/archive" component={Archive}/>
                        <Route path="/statistics" component={Statistics}/>
												<Route path="/edit_positions" component={EditPositions}/>
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
        path: '/create_position'
    },
    {
        name: 'Открытые Позиции',
        path: '/opened_positions'
    },
    {
        name: 'Архив Позиций',
        path: '/archive'
    },
    {
        name: 'Статистика',
        path: '/statistics'
    }
]