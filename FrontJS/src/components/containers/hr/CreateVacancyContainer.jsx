import React, { Component } from 'react';
import {withStyles} from '@material-ui/core/styles';
import { TextField, 
         Button, 
         Select, 
         MenuItem } from '@material-ui/core';
import { PostDataAPI } from '../../../services/PostDataAPI';
import { VACANCIES_URL } from '../../../utils/urls'
import RenderSelectItem from '../../../utils/RenderSelectItem';

  const CityList = [
    'Бишкек',
    'Кара-Балта',
    'Сокулук',
    'Кант'
  ],
  Working_hours = [
    'FULL_TIME',
    'PART_TIME',
    'REMOTE_JOB'
  ];

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
});

class CreateVacancyContainer extends Component { 
    
    constructor(props){
      super(props)
      this.state = {
          request: this.props.requestId,
          created_by: 1,
          title: '',
          city: CityList[0],
          address: 'Бишкек Ахунбаева 119А',
          working_hours: Working_hours[0],
          work_conditions: [
          'работа в комфортном современном офисе в центре города',
          'руководство, готовое поддерживать вас и помогать в развитии',
          'корпоративные вечеринки и совместный отдых',
          'приятные бонусы и премии',
          'компенсации спорта',
          'своевременную оплату труда',
          'возможность бесплатного обучения на курсах наших программистов',
          'удобный график работы',
          'официальное трудоустройство',
          'возможность изучать английский язык с квалифицированным преподавателем прямо в офисе'],
          responsibilities: 'Вам предстоит заниматься разработкой долгосрочных стартап-проектов, которые развиваются на протяжении уже многих лет и являются успешными в своем направлении.',
          salary_min: 0,
          salary_max: 0,
          comments: '',
          image: null
      };
    } 

    handleChange = (event) => {
      this.setState({ 
        [event.target.name]: event.target.value 
      });
    }

    handleSubmit = (event) => {
      event.preventDefault();

      const data = this.state;

      PostDataAPI(VACANCIES_URL, data);
    }

    onFileUpload = (event) => {
      this.setState({
        image: event.target.files[0]
      })
    }

    RenderWorkCondition = (props) => {
      return props.map((item, index) => (
        <MenuItem key={index} value={item.id}>{item.name}</MenuItem>
    ))
    }

    render() {

      const { classes } = this.props;
      const { title,  
              city, 
              address, 
              working_hours,
              responsibilities,
              work_conditions,
              comments,
              salary_min,
              salary_max } = this.state;
      
      return (

        <form onSubmit={this.handleSubmit} style={{ margin: ' 0 1em'}}>
          <div className={classes.root}>
            Название темы: 
            <span className={classes.box}><TextField name='title' value={title} onChange={(e) => this.handleChange(e)} className={classes.box} placeholder='введите название темы' /></span>
          </div>
          <div className={classes.root}>
            Выберите город:
            <span className={classes.box}>
              <Select name='city' onChange={(e) => this.handleChange(e)} value={city} displayEmpty>
                  <MenuItem disabled value=''><em>Выбрать</em></MenuItem>
                  { RenderSelectItem(CityList) }
              </Select>
            </span>
          </div> 
          <div className={classes.root}>
            Адрес Офиса: 
            <span className={classes.box}><TextField name='address' onChange={(e) => this.handleChange(e)} className={classes.box}  placeholder='введите адрес' value={address} /></span>
          </div> 
          <div className={classes.root}>
            График работы:
            <span className={classes.box}>
              <Select name='working_hours' onChange={(e) => this.handleChange(e)} value={working_hours} displayEmpty>
                  <MenuItem disabled value=''><em>Выбрать</em></MenuItem>
                  { RenderSelectItem(Working_hours) }
              </Select>
            </span>
          </div>
          <div>
            Условия работы:
            <div><TextField name='work_conditions' onChange={(e) => this.handleChange(e)} multiline className={classes.textArea} value={work_conditions.map(i => i.toString())} /></div>
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
            <div><TextField name='comments' onChange={(e) => this.handleChange(e)} value={comments} multiline className={classes.textArea} placeholder='введите текст'/></div>
          </div>
          <div className={classes.root}>
            Изображение:
              <span className={classes.box}>
                <input
                  accept='image/*'
                  type='file'
                  name='image'
                />
              </span>
          </div>
          <div>
            <Button type='submit' variant='contained'>Создать</Button>
          </div>
        </form> 
      );
    }
}

export default withStyles(styles)(CreateVacancyContainer);
