import React, {Component} from 'react';
import Header from '../general/Header';
import VacancyListContainer from '../../components/containers/hr/ListOfVacanciesContainer';

class VacancyList extends Component {
    render() {
        return (
            <div>
                <Header title="Открытые Вакансии"/>
                <VacancyListContainer/>
            </div>
        )
    }
}

export default VacancyList;