import React, {Component} from 'react';
import { withStyles } from '@material-ui/core/styles';
import CreateVacancyContainer from '../../components/containers/hr/CreateVacancyContainer';
import Header from '../general/Header';
import { Paper } from '@material-ui/core';

const styles = () => ({
    paperBox: {
        margin: '1.5em 1em',
        padding: '1em 1.5em'
    }
})

class CreateVacancy extends Component {
    render() {
        const { classes } = this.props;

        return (
          <div>
            <Header title="Создать Вакансию" />
              <Paper className={classes.paperBox}>
                <CreateVacancyContainer 
                  requestId={this.props.match.params.id}
                  requestTitle={this.props.match.params.title}/>
              </Paper>
          </div>
        );
    }
}

export default withStyles(styles) (CreateVacancy);
