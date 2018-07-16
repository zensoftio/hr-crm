import React, {Component} from 'react';
import {withStyles} from '@material-ui/core/styles';
import EditVacancyContainer from '../../components/containers/hr/EditVacancyContainer';
import Header from '../general/Header';
import Paper from '@material-ui/core/Paper';

const styles = {
  paperBox: {
    margin: '1em 1.5em',
    padding: '1.5em 1em'
  }
};

class CreateVacancy extends Component {
    render() {
        const {classes} = this.props;

        return (
          <div>
            <Header title="Редактировать Вакансию" />
            <Paper className={classes.paperBox}> 
              <EditVacancyContainer vacancyId={this.props.match.params.id} />
            </Paper>
          </div>
        );
    }
}

export default withStyles(styles)(CreateVacancy);