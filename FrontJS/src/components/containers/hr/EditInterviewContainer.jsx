import React, { Component } from 'react';
import {withStyles} from '@material-ui/core/styles';
import { TextField, MenuItem } from '@material-ui/core';
import { INTERVIEWS_URL } from '../../../utils/urls'
import { FetchDataAPI } from '../../../services/FetchDataAPI';
import { PutDataAPI } from '../../../services/PutDataAPI';
import getStatus from '../../../utils/GetStatus';

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

class EditInterviewContainer extends Component { 

    componentDidMount() {
        FetchDataAPI(INTERVIEWS_URL + '/' + this.props.interviewId)
            .then(interview => this.setState({
                id: interview.id,
                first_name: interview.candidate.first_name,
                last_name: interview.candidate.last_name,
                position: interview.candidate.position.name,
                department: interview.candidate.position.department.name,
                created: interview.candidate.created,
                interviewers: interview.interviewers,
                status: interview.candidate.status,
                date: interview.date
            }))
    }
    
    constructor(props){
      super(props)
      this.state = { 
        id: 0,
        first_name: '',
        last_name: '',
        position: '',
        department: '',
        created: '',
        interviewers: '',
        status: '',
        date: ''
      };
    } 

    handleChange = (event) => {
      this.setState({ 
        [event.target.name]: event.target.value 
      });
    }

    updateInterview = (event) => {
      event.preventDefault();

      const data = this.state;
      PutDataAPI(INTERVIEWS_URL + '/' + this.props.interviewId, data);
    }

    RenderMenuItem = (props) => {
      return props.map((item, index) => (
          <MenuItem key={index} value={item.id}>{item.name}</MenuItem>
      ))
    }

    render() {

      const { classes } = this.props;
      const { first_name,
              last_name,
              position,
              department,
              status,
              date } = this.state;
      const dateTime = new Date(date);
      console.log(dateTime)
      return (

        <div style={{ margin: ' 0 1em'}}>
            <div className={classes.root}>
                Фамилия: 
                <span className={classes.box}><TextField name='first_name' value={first_name} onChange={(e) => this.handleChange(e)} className={classes.box} placeholder='введите фамилию' /></span>
            </div>
            <div className={classes.root}>
                Имя: 
                <span className={classes.box}><TextField name='last_name' value={last_name} onChange={(e) => this.handleChange(e)} className={classes.box} placeholder='введите имя' /></span>
            </div>
            <div className={classes.root}>
                Позиция: 
                <span className={classes.box}><TextField name='position' value={position} onChange={(e) => this.handleChange(e)} className={classes.box} placeholder='введите название позиции' /></span>
            </div>
            <div className={classes.root}>
                Отдел: 
                <span className={classes.box}><TextField name='department' value={department} onChange={(e) => this.handleChange(e)} className={classes.box} placeholder='введите название отдела' /></span>
            </div>
            <div className={classes.root}>
                Статус: 
                <span className={classes.box}><TextField name='status' value={getStatus(status)} onChange={(e) => this.handleChange(e)} className={classes.box} placeholder='введите статус' /></span>
            </div>
           </div> 
      );
    }
}

export default withStyles(styles)(EditInterviewContainer);
