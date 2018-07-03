import React, {Component} from 'react';
import {BrowserRouter, Route, Switch} from 'react-router-dom';
import Navigation from '../general/Navigation';
import Notifications from './Notifications';
import PositionList from './ListPositions';
import CreateVacancy from './CreateVacancy';
import InternList from './ListIntern';
import CandidateList from './ListCandidates';
import OpenedVacancies from './VacancyList';
import Reserve from './Reserve';
import Statistics from '../general/Statistics';
import Error from '../general/Error';
import InterviewList from './ListInterviews';
import SpecifyTheRoute from '../../utils/Route';
import EditInterview from "./EditInterview";

const HRNav = [
    {
        name: "Запросы",
        path: "/list_of_positions",
        component: PositionList
    },
    {
        name: "Создать вакансию",
        path: "/create_vacancy",
        component: CreateVacancy
    },
    {
        name: "Вакансии",
        path: "/opened_vacancies",
        component: OpenedVacancies
    },
    {
        name: "Кандидаты",
        path: "/list_of_candidates",
        component: CandidateList
    },
    {
        name: "Резерв",
        path: "/reserve",
        component: Reserve
    },
    {
        name: "Стажеры",
        path: "/list_of_interns",
        component: InternList
    },
    {
        name: "Статистика",
        path: "/statistics",
        component: Statistics
    },
    {
        name: "Интервью",
        path: "/list_of_interviews",
        component: InterviewList
    },
    {
        name: "Уведомления",
        path: "/notifications",
        component: Notifications
    },
    {
        name: "Изменить интервью",
        path: "/edit_interview",
        component: EditInterview
    }
];

export default class HRScene extends Component {
    render() {
        return (
            <BrowserRouter>
                <div className="container">

                    <Navigation menuItems={HRNav}/>

                    <div className="content">
                        <Switch>
                            <Route path="/" component={OpenedVacancies} exact/>
                            <SpecifyTheRoute route={HRNav}/>
                            <Route component={Error}/>
                        </Switch>
                    </div>
                </div>
            </BrowserRouter>
        );
    }
}