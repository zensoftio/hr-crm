import React, {Component} from 'react';
import {withStyles} from '@material-ui/core/styles';
import TextField from '@material-ui/core/TextField';
import CheckboxLabel from '@material-ui/core/FormControlLabel';
import Button from '@material-ui/core/Button';
import Select from '../../ui/Select';
import MultiSelect from '../../ui/MultipleSelect';
import Checkbox from '@material-ui/core/Checkbox';
import "../../../index.css";

// import { DeleteDataAPI } from '../../../services/DeleteDataAPI';
// import { PutDataAPI } from '../../../services/PutDataAPI';
// import { PostDataAPI } from '../../../services/PostDataAPI';

const RateList = [
        "Стандарт",
        "Премиум",
        "VIP"
    ],
    CityList = [
      "Бишкек",
      "Кара-Балта",
      "Сокулук",
      "Кант",
    ],
    Requirements = [
      "Профильное образование",
      "Опыт работы от 1 до 5 лет (на аналогичной/смежной должности)",
      "Базовое понятие работы JVM",
      "Ясное представление о зоне ответственности компонентов системы"
    ],
    OptionalReq = [
      "Владение Spring Boot",
      "Владение языком Kotlin",
      "Опыт работы с REST API, HTML5 API, сторонними API (Google, FB, Paypal, Stripe и т.д.)"
    ],
    Education = [
      "Среднее",
      "Средне-специальное",
      "Высшее"
    ],
    Experience = [
      "от 1 года",
      "от 2 лет",
      "от 3 лет",
    ],
    Work_conditions = [
        `• работа в комфортном современном офисе в центре города;
    •	руководство, готовое поддерживать вас и помогать в развитии;
    •	корпоративные вечеринки и совместный отдых;
    •	приятные бонусы и премии;
    •	компенсации спорта;
    •	своевременную оплату труда;
    •	возможность бесплатного обучения на курсах наших программистов;
    •	удобный график работы;
    •	официальное трудоустройство;
    •	возможность изучать английский язык с квалифицированным преподавателем прямо в офисе;`
    ],
    Duties = [
        `•	Вам предстоит заниматься разработкой долгосрочных стартап-проектов, которые развиваются на протяжении уже многих лет и являются успешными в своем направлении.`
    ],
    EmploymentType = [
      "Полный рабочий день",
      "Посменно",
    ];

  const styles = theme => ({
    root: {
      display: 'flex',
      justifydefaultValue: 'flex-start',
      alignItems: 'center',
      margin: "1em 0"
    },
    box: {
      margin: '0 1em'
    },
    textArea: {
      width: '100%',
      margin: '1em 0'
    }
});

class CreateVacancyContainer extends Component { 

    constructor(props){
      super(props)
      this.state = {
          topicName: '',
          vacancyName: ''
      };
    }

    handleChange = (event) => {
      this.setState({ 
        [event.target.name]: event.target.value 
      });
    }
    
    handleSubmit = (event) => {
      event.preventDefault();

      // const URL = 'http://0.0.0.0:5000/data/';

      // PostDataAPI(URL, data );
      const data = this.state;
      console.log(data);
    }

    render() {
      const { classes } = this.props;
      const { topicName, vacancyName } = this.state;
 
      return (
        <div  style={{ margin: " 0 1em"}}>
          <div className={classes.root}>
            Тариф Вакансии:
            <span className={classes.box}><Select name="rateList" optionValue={RateList} /></span>
          </div>
          <div className={classes.root}>
            Название темы: 
            <span className={classes.box}><TextField name="topicName" defaultValue={topicName}  onChange={this.handleChange} className={classes.box} placeholder="введите название" /></span>
          </div> 
          <div className={classes.root}>
            Название вакансии: 
            <span className={classes.box}><TextField name="vacancyName" defaultValue={vacancyName} onChange={this.handleChange} className={classes.box} placeholder="введите вакансии" /></span>
          </div>
          <div className={classes.root}>
            Выберите город:
            <span className={classes.box}>
              <Select optionValue={CityList} />
            </span>
          </div> 
          <div className={classes.root}>
            Требования:
            <span className={classes.box}><MultiSelect multiple className={classes.box} optionValue={Requirements} /></span>
          </div> 
          <div className={classes.root}>
            Опциональные требования: 
            <span className={classes.box}><MultiSelect multiple className={classes.box} optionValue={OptionalReq} /></span>
          </div> 
          <div className={classes.root}>
            Адрес Офиса: 
            <span className={classes.box}><TextField className={classes.box} placeholder="введите адрес" defaultValue="Бишкек Ахунбаева 119А" /></span>
          </div> 
          <div className={classes.root}> 
            Образование:
            <span className={classes.box}><Select className={classes.box} optionValue={Education} /></span>
          </div>
          <div className={classes.root}>
            График работы:
            <span className={classes.box}><Select className={classes.box} optionValue={EmploymentType} /></span>
          </div>
          <div className={classes.root}>
            Опыт работы:
            <span className={classes.box}><Select className={classes.box} optionValue={Experience} /></span>
          </div>
          <div>
            Условия работы:
            <div><TextField multiline className={classes.textArea} defaultValue={Work_conditions} /></div>
          </div> 
          <div>
            Обязанности:
            <div><TextField multiline className={classes.textArea} defaultValue={Duties}/></div>
          </div> 
          <div className={classes.root}>
            Зарплата:
              <span className={classes.box}><TextField className={classes.box} placeholder="min" /></span>
              <span className={classes.box}><TextField className={classes.box} placeholder="max" /></span>
          </div>
          <div>
            Прочее:
            <div><TextField multiline className={classes.textArea} placeholder="введите текст"/></div>
          </div>
          <div className={classes.root}>
            Соц. сети:
            <span className={classes.box}>
              <CheckboxLabel 
                control={
                  <Checkbox />
                }
                label="Facebook"
              />
            </span>
            <span className={classes.box}>
              <CheckboxLabel 
                  control={
                    <Checkbox />
                  }
                  label="Job.kg"
                />
            </span>
            <span className={classes.box}>
              <CheckboxLabel 
                  control={
                    <Checkbox />
                  }
                  label="Diesel"
                />
            </span>
          </div>
          <div className={classes.root}>
            Изображение:
              <span className={classes.box}>
                <input
                  accept="image/*"
                  multiple
                  type="file"
                />
              </span>
          </div>
          <div>
            <Button type="button" onClick={this.handleSubmit} variant="contained">Опубликовать</Button>
          </div>
        </div> 
      );
    }
}

export default withStyles(styles)(CreateVacancyContainer);