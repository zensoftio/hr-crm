import React, {Component} from 'react';
import {withStyles} from '@material-ui/core/styles';
import CreateVacancyContainer from '../../components/containers/hr/CreateVacancyContainer';
import Header from '../general/Header';
import Paper from '@material-ui/core/Paper';

const styles = {
    paper: {
        margin: "15px 10px",
        padding: "15px 10px"
    }
};

class CreateVacancy extends Component {

    render() {
        const {classes} = this.props;

        return (
            <div>
                <Header title="Создать Вакансию"/>
                <Paper className={classes.paper}>
                    <CreateVacancyContainer/>
                </Paper>
            </div>
        );
    }
}

export default withStyles(styles)(CreateVacancy);