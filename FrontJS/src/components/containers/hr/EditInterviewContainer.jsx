import React, { Component } from 'react';
import TextField from "@material-ui/core/TextField";
import { withStyles } from "@material-ui/core/styles/index";
import { Link } from "react-router-dom";
import Select from '../../ui/SelectList';

const styles = theme => ({
    label: {
        margin: "10px 15px"
    },
    span: {
        margin: "0 15px"
    }
});

const VacancyList = [
    "Junior JS Developer",
    "Middle Java Developer",
    "Senior Python Developer"
];

class EditInterviewContainer extends Component {
    render() {
        const {classes} = this.props;
        return (
            <div>
                <div className={classes.label}>
                    <span className={classes.span}>Ф.И.О:</span>
                    <TextField placeholder="Имя Фамилия"/>
                </div>
                <div className={classes.label}>
                    <span className={classes.span}>МЕСТО:</span>
                    <TextField placeholder="Place"/>
                </div>
                <div className={classes.label}>
                    <span className={classes.span}>ДАТА:</span>
                    <Link to={'date_picker_link'}>{Date()} </Link>;
                </div>
                <div className={classes.label}>
                    <span className={classes.span}>ВАКАНСИЯ:</span>
                    <Select optionValue={VacancyList}/>
                </div>
                <div className={classes.label}>
                    <span className={classes.span}>ИНТЕРВЬЮЕР:</span>
                    <TextField placeholder="Имя Фамилия" value="Name Surname"/>
                    <button>Remove</button>
                    <br/>
                    <button>ДОБАВИТЬ ИНТЕРВЬЮЕР</button>
                </div>
                <div className={classes.label}>
                    <button>СОХРАНИТЬ</button>
                    <button>УДАЛИТЬ</button>
                    <button>ОТКРЫТЬ ПРОФИЛЬ</button>
                </div>

            </div>
        );
    }

}

export default withStyles(styles)(EditInterviewContainer);