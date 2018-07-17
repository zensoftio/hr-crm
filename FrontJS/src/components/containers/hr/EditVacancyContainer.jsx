import React, { Component } from 'react';
import {withStyles} from '@material-ui/core/styles';
import { TextField, 
         Button, 
         Paper, 
         MenuItem,
         ListItemText,
         ListItem,
         Typography, 
         FormControlLabel,
         Checkbox} from '@material-ui/core';
import { VACANCIES_URL } from '../../../utils/urls'
import { FetchDataAPI } from '../../../services/FetchDataAPI';
import DateConvert from '../../../utils/DateConvert';
import { PutDataAPI } from '../../../services/PutDataAPI';

  const styles = () => ({
    root: {
      display: 'flex',
      justifyvalue: 'flex-start',
      alignItems: 'center',
      margin: '1em 0'
    },
    box: {
      margin: '0 1em'
    },
    textArea: {
      width: '100%',
      margin: '1em 0'
    },
    commentBox: {
        width: '100%'
    }
});

class EditVacancyContainer extends Component { 

    componentDidMount() {
        FetchDataAPI(VACANCIES_URL + '/' + this.props.vacancyId)
            .then(vacancy => this.setState({
                name: vacancy.name,
                title: vacancy.title,
                city: vacancy.city,
                address: vacancy.address,
                work_conditions: vacancy.work_conditions,
                working_hours: vacancy.working_hours,
                salary_min: vacancy.salary_min,
                salary_max: vacancy.salary_max,
                image: vacancy.image,
                responsibilities: vacancy.responsibilities,
                comments: vacancy.comments,
                created: vacancy.created,
                last_published: vacancy.last_published
            }))
    }
    
    constructor(props){
      super(props)
      this.state = {
        name: '',
        title: '',
        city: '',
        address: '',
        work_conditions: [],
        working_hours: '',
        salary_min: 0,
        salary_max: 0,
        image: null,
        responsibilities: '',
        comments: '',
        created: '',
        last_published: '',
        facebook: false,
        diesel: false,
        job: false
      };
    } 

    handleChange = (event) => {
      this.setState({ 
        [event.target.name]: event.target.value 
      });
    }

    updateVacancy = (event) => {
      event.preventDefault();

      const data = this.state;
      PutDataAPI(VACANCIES_URL + '/' + this.props.vacancyId, data);
    }

    publishVacancy = (event) => {
        window.open('https://www.facebook.com/v3.0/dialog/oauth?%20client_id=1482536371851716&redirect_uri=https://reachthestars.ml&response_type=token', "Login Facebook", "width=600,height=450");
    }

    RenderMenuItem = (props) => {
      return props.map((item, index) => (
          <MenuItem key={index} value={item.id}>{item.name}</MenuItem>
      ))
    }

    handleCheckbox = (e) => {
        this.setState({
            [e.target.name]: e.target.checked
        })
        console.log(this.state)
    }

    render() {

      const { classes } = this.props;
      const { name,  
              title, 
              address, 
              city,
              working_hours,
              responsibilities,
              work_conditions,
              comments,
              salary_min,
              salary_max,
              created,
              last_published } = this.state;
      return (

        <div style={{ margin: ' 0 1em'}}>
            <div className={classes.root}>
                Название вакансии: 
                <span className={classes.box}><TextField name='name' value={name} onChange={(e) => this.handleChange(e)} className={classes.box} placeholder='введите название вакансии' /></span>
            </div>
            <div className={classes.root}>
                Название темы: 
                <span className={classes.box}><TextField name='title' value={title} onChange={(e) => this.handleChange(e)} className={classes.box} placeholder='введите название темы' /></span>
            </div>
            <div className={classes.root}>
                Выберите город:
                <span className={classes.box}>
                    <TextField name='city' value={city} onChange={(e) => this.handleChange(e)} className={classes.box} placeholder='введите город' />
                </span> 
            </div> 
            <div className={classes.root}>
                Адрес Офиса: 
                <span className={classes.box}><TextField name='address' onChange={(e) => this.handleChange(e)} className={classes.box}  placeholder='введите адрес' value={address} /></span>
            </div> 
            <div className={classes.root}>
                График работы:
                <span className={classes.box}>
                    <TextField name='working_hours' onChange={(e) => this.handleChange(e)} multiline className={classes.textArea} value={working_hours} />
                </span>
            </div>
            <div>
                Условия работы:
                <div><TextField name='work_conditions' onChange={(e) => this.handleChange(e)} multiline className={classes.textArea} value={work_conditions}/></div>
            </div> 
            <div>
                Обязанности:
                <div><TextField name='responsibilities' onChange={(e) => this.handleChange(e)} multiline className={classes.textArea} value={responsibilities}/></div>
            </div> 
            <div className={classes.root}>
                Зарплата:
                <span className={classes.box}><TextField type='number' value={salary_min} name='salary_min' onChange={(e) => this.handleChange(e)} className={classes.box} placeholder='min' /></span>
                <span className={classes.box}><TextField type='number' value={salary_max} name='salary_max' onChange={(e) => this.handleChange(e)} className={classes.box} placeholder='max' /></span>
            </div>
            <div>
                Комментарии:
                <div className={classes.root}>
                    <Paper className={classes.commentBox}>
                        { <ListItem><ListItemText>{comments}</ListItemText></ListItem>  }
                    </Paper>
                </div>
            </div>
            <div className={classes.root}>
                Изображение:
                <span className={classes.box}>
                    <input
                    accept='image/*'
                    type='file'
                    name='image'
                    onChange={(e) => this.handleChange(e)}
                    // value={image}
                    />
                </span>
            </div>
            <div className={classes.box }>
                <Typography color="textSecondary">
                    История изменений
                </Typography>
                <Typography component="h5">
                    <div className={classes.root}>вакансия создана: { DateConvert(created) }</div>
                    <div className={classes.root}>последняя публикация: { DateConvert(last_published) }</div>
                </Typography>
            </div>
            <div className={classes.root}>
                Соц. сети
                <div className={classes.box}>
                    <FormControlLabel
                        control={
                        <Checkbox
                            checked={this.state.facebook}
                            onChange={(e) => this.handleCheckbox(e)}
                            value={this.state.facebook}
                            name="facebook"
                        />
                        }
                        label="Facebook"
                    />
                    <FormControlLabel
                        control={
                        <Checkbox
                            checked={this.state.job}
                            onChange={(e) => this.handleCheckbox(e)}
                            value={this.state.job}
                            name="job"
                        />
                        }
                        label="Job.KG"
                    />
                    <FormControlLabel
                        control={
                        <Checkbox
                            checked={this.state.diesel}
                            onChange={(e) => this.handleCheckbox(e)}
                            value={this.state.diesel}
                            name="diesel"
                        />
                        }
                        label="Diesel"
                    />
                </div>
                
            </div>
            <div className={classes.root}>
                <div className={classes.box}><Button type='button' variant='contained' onClick={this.updateVacancy} color="primary">Изменить</Button></div>
                <div className={classes.box}><Button type='button' variant='contained' onClick={this.publishVacancy}>Опубликовать</Button></div>
            </div>
        </div> 
      );
    }
}

export default withStyles(styles)(EditVacancyContainer);
