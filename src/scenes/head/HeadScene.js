import React, { Component } from 'react';
import { BrowserRouter, Route, Switch } from 'react-router-dom';
import Home from '../../scenes/general/Home';
import OpenedPositions from '../../scenes/head/OpenedPositions';
import Error from '../general/Error';
import Navigation from '../../scenes/general/Navigation';
import CreatePosition from '../../scenes/head/CreatePosition';
import Archive from '../../scenes/head/Archive';
import Statistics from '../../scenes/general/Statistics';
import '../../index.css';

export default class HeadScene extends Component {
    render() {
        return(
            <BrowserRouter>
                <div className="container">

                <Navigation menuItems={HeadNav}/>

                <div className="content">
                    <Switch>
                        <Route path="/" component={Home} exact/>
                        <Route path="/create_position" component={CreatePosition}/>   
                        <Route path="/opened_positions" component={OpenedPositions}/>
                        <Route path="/archive" component={Archive}/>
                        <Route path="/statistics" component={Statistics}/>
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
        name: 'Home',
        path: '/'
    },
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