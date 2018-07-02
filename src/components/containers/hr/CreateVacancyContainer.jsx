import React, { Component } from 'react';
import Input from '../../ui/Input';
import TextArea from '../../ui/TextArea';
import ButtonSubmit from '../../ui/ButtonSubmit';
import Select from '../../ui/SelectList';
import Grid from '@material-ui/core/Grid';
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

const styles = ({
  button_upload: {
    display: 'none'
  }
})
  


class CreateVacancyContainer extends Component {
  render() {

    return (
      <div>
        <Grid item lg={6}>
            <div>
              Тариф Вакансии: 
              <Select vals={RateList} />
            </div>
            <div>
              Название темы: 
              <Input placeholder="введите название" />
            </div> 
            <div>
              Название темы: 
              <Input placeholder="введите название" />
            </div>
            <div>
              Выберите город:
              <Select vals={CityList} />
            </div> 
            <div>
              Требования:
              <MultiSelect names={Requirements} />
            </div> 
            <div>
              Опциональные требования: 
              <Input placeholder="введите требования" />
            </div> 
            <div>
              Адрес Офиса: 
              <Input placeholder="введите адрес" value="Бишкек Ахунбаева 119А" />
            </div> 
            <div>
              Образование:
              <Select vals={Education} />
            </div>
            <div>
              График работы:
              <Input placeholder="введите график" />
            </div>
            <div>
              Опыт работы:
              <Select vals={Experience} />
            </div>
            <div>
              Условия работы:
              <TextArea  value={Work_conditions}/>
            </div> 
            <div>
              Обязанности:
              <TextArea  value={Duties}/>
            </div> 
            <div>
              Зарплата:
              <div>
                Min: <Input placeholder="min" />
              </div>
              <div>
                Max: <Input placeholder="max" />
              </div>
            </div>
            <div>
              Тип занятости:
              <Select vals={EmploymentType} />
            </div>
            <div>
              Прочее:
              <TextArea  placeholder="введите текст"/>
            </div>
            <div>
              Соц. сети:
              <Checkbox label="Facebook"/>
              <Checkbox label="Job.kg"/>
              <Checkbox label="Diesel"/>
            </div>
            <div>
              Изображение:
              <input
                accept="image/*"
                multiple
                type="file"
                className={styles.button_upload}
              />
            </div>
            <div>
              <ButtonSubmit>Опубликовать</ButtonSubmit>
            </div>
        </Grid >

      </div>
    );
  }
}

export default CreateVacancyContainer;