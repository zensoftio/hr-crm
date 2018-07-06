import React, {Component} from 'react';
import {withStyles} from '@material-ui/core/styles';
import Input from '../../ui/Input';
import TextArea from '../../ui/TextArea';
import Button from '../../ui/ButtonSubmit';
import Select from '../../ui/SelectList';
import MultiSelect from '../../ui/MultipleSelect';
import Checkbox from '../../ui/Checkbox';
import "../../../index.css";
// import { DeleteDataAPI } from '../../../services/DeleteDataAPI';
// import { PutDataAPI } from '../../../services/PutDataAPI';
import { PostDataAPI } from '../../../services/PostDataAPI';

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
        "one",
        "three",
        "four",
        "five"
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
    }
});

class CreateVacancyContainer extends Component { 

    constructor(props){
      super(props)
      this.state = {
          topicName: ''
      };
    }
    handleChange = (event) => {
      this.setState({ 
        [event.target.name]: event.target.defaultValue 
      });
    }
    
    handleSubmit = (event) => {
      event.preventDefault();

      const URL = 'http://0.0.0.0:5000/data/';
      const data = this.state.topicName;

      PostDataAPI(URL, data );
    }

    render() {
      const { classes } = this.props;
          
      return (
        <div  style={{ margin: " 0 1em"}}>dfd
          <div className={classes.root}>
            Тариф Вакансии:
            <span className={classes.box}><Select vals={RateList} /></span>
          </div>
          <div className={classes.root}>
            Название темы: 
            <span className={classes.box}><Input name="topicName" defaultValue={this.state.topicName}  onChange={this.handleChange} className={classes.box} placeholder="введите название" /></span>
          </div> 
          <div className={classes.root}>
            Название вакансии: 
            <span className={classes.box}><Input className={classes.box} placeholder="введите вакансии" /></span>
          </div>
          <div className={classes.root}>
            Выберите город:
            <span className={classes.box}><Select className={classes.box} vals={CityList} /></span>
          </div> 
          <div className={classes.root}>
            Требования:
            <span className={classes.box}><MultiSelect className={classes.box} names={Requirements} /></span>
          </div> 
          <div className={classes.root}>
            Опциональные требования: 
            <span className={classes.box}><Input className={classes.box} placeholder="введите требования" /></span>
          </div> 
          <div className={classes.root}>
            Адрес Офиса: 
            <span className={classes.box}><Input className={classes.box} placeholder="введите адрес" defaultValue="Бишкек Ахунбаева 119А" /></span>
          </div> 
          <div className={classes.root}> 
            Образование:
            <span className={classes.box}><Select className={classes.box} vals={Education} /></span>
          </div>
          <div className={classes.root}>
            График работы:
            <span className={classes.box}><Input className={classes.box} placeholder="введите график" /></span>
          </div>
          <div className={classes.root}>
            Опыт работы:
            <span className={classes.box}><Select className={classes.box} vals={Experience} /></span>
          </div>
          <div>
            Условия работы:
            <div><TextArea className={classes.box} defaultValue={Work_conditions} /></div>
          </div> 
          <div>
            Обязанности:
            <div><TextArea className={classes.box} defaultValue={Duties}/></div>
          </div> 
          <div className={classes.root}>
            Зарплата:
              <span className={classes.box}><Input className={classes.box} placeholder="min" /></span>
              <span className={classes.box}><Input className={classes.box} placeholder="max" /></span>
          </div>
          <div className={classes.root}>
            Тип занятости:
            <span className={classes.box}><Select className={classes.box} vals={EmploymentType} /></span>
          </div>
          <div>
            Прочее:
            <div><TextArea className={classes.box} placeholder="введите текст"/></div>
          </div>
          <div className={classes.root}>
            Соц. сети:
            <span className={classes.box}><Checkbox label="Facebook"/></span>
            <span className={classes.box}><Checkbox label="Job.kg"/></span>
            <span className={classes.box}><Checkbox label="Diesel"/></span>
            
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