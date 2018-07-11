import React, {Component} from 'react';
import PropTypes from 'prop-types';
import {withStyles} from '@material-ui/core/styles';
import { TextField, 
         Button, 
         Select, 
         MenuItem } from '@material-ui/core';
import { PostDataAPI } from '../../../services/PostDataAPI';

  const CityList = [
    "Бишкек",
    "Кара-Балта",
    "Сокулук",
    "Кант",
  ],
  Working_hours = [
    {
      id: 0,
      name: "Полный рабочий день"
    },
    {
      id: 1,
      name: "Гибкий график"
    },
    {
      id: 2,
      name: "Удаленная работа"
    }
  ];

  const styles = () => ({
    root: {
      display: 'flex',
      justifyvalue: 'flex-start',
      alignItems: 'center',
      margin: "1em 0"
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
          request: 1,
          created_by: 1,
          title: '',
          city: '',
          address: 'Бишкек Ахунбаева 119А',
          working_hours: '',
          work_conditions: [
            `работа в комфортном современном офисе в центре города;`,
            `руководство, готовое поддерживать вас и помогать в развитии;`,
            `корпоративные вечеринки и совместный отдых;`,
            `приятные бонусы и премии;`,
            `компенсации спорта;`,
            `своевременную оплату труда;`,
            `возможность бесплатного обучения на курсах наших программистов;`,
            `удобный график работы;`,
            `официальное трудоустройство;`,
            `возможность изучать английский язык с квалифицированным преподавателем прямо в офисе;`
          ],
          responsibilities: `Вам предстоит заниматься разработкой долгосрочных стартап-проектов, которые развиваются на протяжении уже многих лет и являются успешными в своем направлении.`,
          salary_min: '',
          salary_max: '',
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
      const URL = 'http://159.65.153.5/api/v1/vacancies';

      const data = this.state;
      PostDataAPI(URL, data);
    }

    onFileUpload = event => {
      this.setState({
        image: event.target.files[0]
      })
    }

    RenderSelectItem = (props) => {
      return props.map((item, index) => (
          <MenuItem key={index} value={item}>{item}</MenuItem>
      ))
    }

    RenderWorkCondition = (props) => {
      return props.map((item, index) => (
          <MenuItem key={index} value={item.id}>{item.name}</MenuItem>
      ))
    }

    render() {
      console.log(this.props.data)
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

        <form onSubmit={this.handleSubmit} style={{ margin: " 0 1em"}}>
          <div className={classes.root}>
            Название вакансии: 
            <span className={classes.box}><TextField name="title" defaultValue={title} onChange={this.handleChange} className={classes.box} placeholder="введите вакансии" /></span>
          </div>
          <div className={classes.root}>
            Выберите город:
            <span className={classes.box}>
              <Select name="city" onChange={this.handleChange} value={city} displayEmpty>
                  <MenuItem disabled value=""><em>Выбрать</em></MenuItem>
                  { this.RenderSelectItem(CityList) }
              </Select>
            </span>
          </div> 
          <div className={classes.root}>
            Адрес Офиса: 
            <span className={classes.box}><TextField name="address" onChange={this.handleChange} className={classes.box}  placeholder="введите адрес" defaultValue={address} /></span>
          </div> 
          <div className={classes.root}>
            График работы:
            <span className={classes.box}>
              <Select name="working_hours" onChange={this.handleChange} value={working_hours} displayEmpty>
                  <MenuItem disabled value=""><em>Выбрать</em></MenuItem>
                  { this.RenderWorkCondition(Working_hours) }
              </Select>
            </span>
          </div>
          <div>
            Условия работы:
            <div><TextField name="work_conditions" onChange={this.handleChange} multiline className={classes.textArea} defaultValue={work_conditions} /></div>
          </div> 
          <div>
            Обязанности:
            <div><TextField name="responsibilities" onChange={this.handleChange} multiline className={classes.textArea} defaultValue={responsibilities}/></div>
          </div> 
          <div className={classes.root}>
            Зарплата:
              <span className={classes.box}><TextField type="number" defaultValue={salary_min} name="salary_min" onChange={this.handleChange} className={classes.box} placeholder="min" /></span>
              <span className={classes.box}><TextField type="number" defaultValue={salary_max} name="salary_max" onChange={this.handleChange} className={classes.box} placeholder="max" /></span>
          </div>
          <div>
            Прочее:
            <div><TextField  name="comments" onChange={this.handleChange} defaultValue={comments} multiline className={classes.textArea} placeholder="введите текст"/></div>
          </div>
          <div className={classes.root}>
            Изображение:
              <span className={classes.box}>
                <input
                  accept="image/*"
                  type="file"
                  name="image"
                />
              </span>
          </div>
          <div>
            <Button type="submit" variant="contained">Опубликовать</Button>
          </div>
        </form> 
      );
    }
}

CreateVacancyContainer.propTypes = {
  classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(CreateVacancyContainer);
