import React, {Component} from 'react';
import {BrowserRouter, Route, Switch} from 'react-router-dom';
import Navigation from '../general/Navigation';
import Notifications from './Notifications';
import PositionList from './ListPositions';
import CreateVacancy from './CreateVacancy';
import InternList from './ListIntern';
import CandidateList from './ListCandidates';
import EditVacancy from './EditVacancy';
import OpenedVacancies from './VacancyList';
import Reserve from './Reserve';
import Statistics from '../general/Statistics';
import Error from '../general/Error';
import InterviewList from './ListInterviews';
import SpecifyTheRoute from '../../utils/Route';
import Profile from './CandidateProfile';
import EditInterview from "./EditInterview";

const HRNav = [
    {
        name: "Запросы",
        path: "/list_of_positions",
        component: PositionList
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
        name: "",
        path: "/edit_interview/:id",
        component: EditInterview
    },
    {
        name: "",
        path: "/profile/:id",
        component: Profile
		},
		{
			name: "",
			path: "/create_vacancy/:id",
			component: CreateVacancy
		},
		{
			name: "",
			path: "/vacancy/:id",
			component: EditVacancy
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
                            <Route path="/" component={PositionList} exact/>
                            <SpecifyTheRoute route={HRNav}/>
                            <Route path="*" component={Error}/>
                        </Switch>
                    </div>
                </div>
            </BrowserRouter>
        );
    }
}
