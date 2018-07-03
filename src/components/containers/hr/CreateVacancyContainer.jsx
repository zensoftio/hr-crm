import React, {Component} from 'react';
import {withStyles} from '@material-ui/core/styles';
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

const styles = theme => ({
    label: {
        margin: "10px 15px"
    },
    span: {
        margin: "0 15px"
    }
});

class CreateVacancyContainer extends Component {
    render() {
        const {classes} = this.props;

        return (
            <div>
                <Grid item lg={8}>
                    <div className={classes.label}>
                        <span className={classes.span}>Тариф Вакансии:</span>
                        <Select vals={RateList}/>
                    </div>
                    <div className={classes.label}>
                        <span className={classes.span}>Название темы:</span>
                        <Input placeholder="введите название"/>
                    </div>
                    <div className={classes.label}>
                        <span className={classes.span}>Название темы:</span>
                        <Input placeholder="введите название"/>
                    </div>
                    <div className={classes.label}>
                        <span className={classes.span}>Выберите город:</span>
                        <Select vals={CityList}/>
                    </div>
                    <div className={classes.label}>
                        <span className={classes.span}>Требования:</span>
                        <MultiSelect names={Requirements}/>
                    </div>
                    <div className={classes.label}>
                        <span className={classes.span}>Опциональные требования:</span>
                        <Input placeholder="введите требования"/>
                    </div>
                    <div className={classes.label}>
                        <span className={classes.span}>Адрес Офиса:</span>
                        <Input placeholder="введите адрес" value="Бишкек Ахунбаева 119А"/>
                    </div>
                    <div className={classes.label}>
                        <span className={classes.span}>Образование:</span>
                        <Select vals={Education}/>
                    </div>
                    <div className={classes.label}>
                        <span className={classes.span}>График работы:</span>
                        <Input placeholder="введите график"/>
                    </div>
                    <div className={classes.label}>
                        <span className={classes.span}>Опыт работы:</span>
                        <Select vals={Experience}/>
                    </div>
                    <div className={classes.label}>
                        <span className={classes.span}>Условия работы:</span>
                        <TextArea value={Work_conditions}/>
                    </div>
                    <div className={classes.label}>
                        <span className={classes.span}>Обязанности:</span>
                        <TextArea value={Duties}/>
                    </div>
                    <div className={classes.label}>
                        <span className={classes.span}>Зарплата:</span>
                        <span className={classes.span}><Input placeholder="min"/></span>
                        <span className={classes.span}><Input placeholder="max"/></span>
                    </div>
                    <div className={classes.label}>
                        <span className={classes.span}>Тип занятости:</span>
                        <Select vals={EmploymentType}/>
                    </div>
                    <div className={classes.label}>
                        <span className={classes.span}>Прочее:</span>
                        <TextArea placeholder="введите текст"/>
                    </div>
                    <div className={classes.label}>
                        <span className={classes.span}>Соц. сети:</span>
                        <Checkbox label="Facebook"/>
                        <Checkbox label="Job.kg"/>
                        <Checkbox label="Diesel"/>
                    </div>
                    <div className={classes.label}>
                        Изображение:
                        <input
                            accept="image/*"
                            multiple
                            type="file"
                            className={classes.label}
                        />
                    </div>
                    <div className={classes.label}>
                        <ButtonSubmit>Опубликовать</ButtonSubmit>
                    </div>
                </Grid>

            </div>
        );
    }
}

export default withStyles(styles)(CreateVacancyContainer);