import React, { Component } from 'react';
import CreateVacancyContainer from '../../components/containers/hr/CreateVacancyContainer';
import Header from '../general/Header';

export default class CreateVacancy extends Component {
 
  render() {

    return (
      <div>
        <Header title="Создать Вакансию" />
        <CreateVacancyContainer />
      </div>
    );
  }
}

