import React, {Component} from 'react';
import {withStyles} from '@material-ui/core/styles';
import Input from '../../ui/Input';
import TextArea from '../../ui/TextArea';
import ButtonSubmit from '../../ui/ButtonSubmit';
import Select from '../../ui/SelectList';
import MultiSelect from '../../ui/MultipleSelect';
import Checkbox from '../../ui/Checkbox';
import "../../../index.css";

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
      alignItems: 'center',
      justifyContent: 'start'
    },
    box: {
      margin: '1em 1.5em'
    },
    inlineBox: {
      display: 'inline-block'
    }
});

class CreateVacancyContainer extends Component {
    render() {
        const {classes} = this.props;
    return (
      <div>
        <div>
          <span className={classes.box}>Тариф Вакансии:</span>  
          <Select vals={RateList} />
        </div>
        <div>
          <span className={classes.box}>Название темы:</span> 
          <Input placeholder="введите название" />
        </div> 
        <div>
          <span className={classes.box}>Название темы:</span> 
          <Input placeholder="введите название" />
        </div>
        <div>
          <span className={classes.box}>Выберите город:</span>
          <Select vals={CityList} />
        </div> 
        <div>
          <span className={classes.box}>Требования:</span>
          <MultiSelect names={Requirements} />
        </div> 
        <div>
          <span className={classes.box}>Опциональные требования:</span> 
          <Input placeholder="введите требования" />
        </div> 
        <div>
          <span className={classes.box}>Адрес Офиса:</span> 
          <Input placeholder="введите адрес" value="Бишкек Ахунбаева 119А" />
        </div> 
        <div>
          <span className={classes.box}>Образование:</span>
          <Select vals={Education} />
        </div>
        <div>
          <span className={classes.box}>График работы:</span>
          <Input placeholder="введите график" />
        </div>
        <div>
          <span className={classes.box}>Опыт работы:</span>
          <Select vals={Experience} />
        </div>
        <div>
          <span className={classes.box}>Условия работы:</span>
          <div className={classes.box}><TextArea  value={Work_conditions} /></div>
        </div> 
        <div>
          <span className={classes.box}>Обязанности:</span>
          <div className={classes.box}><TextArea  value={Duties}/></div>
        </div> 
        <div>
          <span className={classes.box}>Зарплата:</span>
            <span className={classes.box}><Input placeholder="min" /></span>
            <span className={classes.box}><Input placeholder="max" /></span>
        </div>
        <div>
          <span className={classes.box}>Тип занятости:</span>
          <Select vals={EmploymentType} />
        </div>
        <div>
          <span className={classes.box}>Прочее:</span>
          <div className={classes.box}><TextArea  placeholder="введите текст"/></div>
        </div>
        <div className={classes}>
          <span >Соц. сети:
          <Checkbox label="Facebook"/>
          <Checkbox label="Job.kg"/>
          <Checkbox label="Diesel"/>
          </span>
          
        </div>
        <div className={classes.box}>
          Изображение:
            <input
              accept="image/*"
              multiple
              type="file"
            />
        </div>
        <div className={classes.box}>
          <ButtonSubmit>Опубликовать</ButtonSubmit>
        </div>
      </div>
    );
  }
}
export default withStyles(styles)(CreateVacancyContainer);