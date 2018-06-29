import React, { Component } from 'react';
import { BrowserRouter, Route, Switch } from 'react-router-dom';
import Home from '../general/Home';
import Navigation from '../general/Navigation';
import Notifications from './Notifications';
import PositionList from './ListPositions';
import CreateVacancy from './CreateVacancy';
import InternList from './ListIntern';
import CandidateList from  './ListCandidates';
import OpenedVacancies from './VacancyList';
import Reserve from './Reserve';
import Statistics from '../general/Statistics';
import Error from '../general/Error';
import InterviewList from './InterviewList';

const HRNav = [
    {
        name: "Запросы",
        path: "/list_of_positions",
    },
    {
        name: "Создать вакансию",
        path: "/create_vacancy"
    },
    {
        name: "Вакансии",
        path: "/opened_vacancies"
    },
    {
        name: "Кандидаты",
        path: "/list_of_candidates"
    },
    {
        name: "Резерв",
        path: "/reserve"
    },
    {
        name: "Стажеры",
        path: "/list_of_interns"
    },
    {
        name: "Статистика",
        path: "/statistics"
    },
    {
        name: "Интервью",
        path: "/list_of_interviews"
    },
    {
        name: "Уведомления",
        path: "/notifications"
    }
];

export default class HRScene extends Component {
    render() {
        return(
            <BrowserRouter>
                <div className="container">

                    <Navigation menuItems={HRNav} />

                    <div className="content">
                        <Switch>
                            <Route path="/" component={Home}/>
                            <Route path="/list_of_positions" component={PositionList}/>
                            <Route path="/create_vacancy" component={CreateVacancy}/>
                            <Route path="/opened_vacancies" component={OpenedVacancies}/>
                            <Route path="/list_of_candidates" component={CandidateList}/>   
                            <Route path="/reserve" component={Reserve}/>
                            <Route path="/list_of_interns" component={InternList}/>   
                            <Route path="/statistics" component={Statistics}/>
                            <Route path="/list_of_interviews" component={InterviewList}/>
                            <Route path="/notifications" component={Notifications}/>
                            <Route component={Error}/>
                        </Switch>
                    </div>
                </div>
            </BrowserRouter>
        );
    }
}