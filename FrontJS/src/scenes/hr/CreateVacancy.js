import React, {Component} from 'react';
import CreateVacancyContainer from '../../components/containers/hr/CreateVacancyContainer';
import Header from '../general/Header';

class CreateVacancy extends Component {
    render() {
        return (
          <div>
            <Header title="Создать Вакансию" />
              <CreateVacancyContainer vacancyId={this.props.match.params.id}/>
          </div>
        );
    }
}

export default CreateVacancy;
