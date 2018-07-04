import React, { Component } from 'react';
import { withStyles } from '@material-ui/core/styles';
import Header from '../general/Header';
import Input from '../../components/ui/Input';
import Select from '../../components/ui/SelectList';
import Paper from '@material-ui/core/Paper';
import TextArea from '../../components/ui/TextArea';
import Button from '../../components/ui/ButtonSubmit';
import Divider from '@material-ui/core/Divider';
import ModalButton from '../../components/ui/ModalWindow';
import DateTimePicker from '../../components/ui/DateTimePicker';

const Department = [
    "PM",
    "DevOps",
    "Administrative",
    "Developer"
],
    Experience = [
        "2 года"
    ],
    Level = [
        "Junior",
        "Middle",
        "Senior"
    ],
    Status = [
        "активен",
        "неактивен",
        "резерв"
    ],
    Interviewers = [
        'Имя Фамилия',
        'Имя Фамилия',
        'Имя Фамилия' 
    ],
    TopicTemp = [
        "Отправка ТЗ",
        "Приглашение",
        "Прочее",
    ],
    MsgTemp = [
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur."
    ]
 
const style = {
    box: {
        margin: '1em .5em'
    },
    paperBox: {
        margin: '1em 1.5em',
        padding: '1.5em 1em'
    },
    modal: {
        display: "flex",
        flexDirection: "row",
    }
}

class UserProfile extends Component {

    render() { 
        const { classes } = this.props;

        return (
            <div>
                <Header title="Профиль" />
                <Paper className={classes.paperBox}> 
                    <div>
                        <span className={classes.box}>Фамилия:</span>   
                        <Input placeholder="введите фамилию" />
                    </div>
                    <div>
                        <span className={classes.box}>Имя:</span>   
                        <Input placeholder="введите имя" />
                    </div>
                    <div>
                        <span className={classes.box}>Email:</span>   
                        <Input placeholder="введите email" />
                    </div>
                    <div>
                        <span className={classes.box}>Номер:</span>   
                        <Input placeholder="введите номер" />
                    </div>
                    <div>
                        <span className={classes.box}>Skype:</span>   
                        <Input placeholder="введите адрес" />
                    </div>
                    <div style={{margin: "1em 0"}}>
                        <span className={classes.box}>Отдел:</span>   
                        <Select vals={Department}/>
                    </div>
                    <div style={{margin: "1em 0"}}>
                        <span className={classes.box}>Опыт:</span>   
                        <Select vals={Experience}/>
                    </div>
                    <div style={{margin: "1em 0"}}>
                        <span className={classes.box}>Уровень:</span>   
                        <Select vals={Level}/>
                    </div>
                    <div style={{margin: "1em 0"}}>
                        <span className={classes.box}>Статус:</span>   
                        <Select vals={Status}/>
                    </div>
                    <div>
                        <span className={classes.box}>Резюме:</span>
                        <a href=""> CV.pdf </a>
                    </div>
                    <div className={classes.box}>
                        Комментарий:
                        <TextArea />
                    </div>
                    <div className={classes.box}>
                        <Button>Создать комментарий</Button>
                    </div>
                    <div className={classes.paperBox}>
                        <Divider />
                    </div>
                    <div className={classes.modal}>
                        <span className={classes.box}><ModalButton
                            title="Заполните все поля"
                            text={
                                <div>
                                    <div className={classes.box}><DateTimePicker /></div>
                                    <div className={classes.box}>Интервьювер:
                                        <Select vals={Interviewers}/>
                                    </div>
                                    <div className={classes.box}>Место:
                                        <Input placeholder="место проведения"/>
                                    </div>
                                    <div className={classes.box}>Сообщение:
                                        <TextArea />
                                    </div>
                                </div> 
                            }
                            rightBtn="отправить"
                            leftBtn="закрыть"
                        >Пригласить на интервью</ModalButton></span>
                        <span className={classes.box}>
                            <ModalButton>Нанять</ModalButton>
                        </span>
                        <span className={classes.box}>
                            <ModalButton
                                title="Вы действительно хотите удалить?"
                                leftBtn="ДА"
                                rightBtn="НЕТ"
                            >Удалить</ModalButton>
                        </span>
                        <span className={classes.box}>
                            <ModalButton>Сохранить</ModalButton>
                        </span>
                        <span className={classes.box}>
                            <ModalButton
                                title="Отправить тестовое задание"
                                text={
                                    <div>
                                        <div className={classes.box}>Тема:
                                            <Select vals={TopicTemp}/>
                                        </div>
                                        <div className={classes.box}>Сообщение:
                                            <TextArea value={MsgTemp}/>
                                        </div>
                                        <div className={classes.box}>Ссылка на задание:
                                            <a href="">document.docx</a>
                                        </div>
                                    </div>
                                }
                                leftBtn="закрыть"
                                rightBtn="отправить"
                            >Отправить ТЗ</ModalButton>
                        </span>
                        <span className={classes.box}>
                            <ModalButton
                                title="Отправить сообщение"
                                text={
                                    <div>
                                        <div className={classes.box}>Тема:
                                            <Input placeholder="тема сообщения"/>
                                        </div>
                                        <div className={classes.box}>Сообщение:
                                            <TextArea value={MsgTemp}/>
                                        </div>
                                    </div>
                                }
                                leftBtn="закрыть"
                                rightBtn="отправить"
                            >Отправить письмо</ModalButton>
                        </span>
                    
                    </div>
                </Paper>
            </div>
        )
    }
}

export default withStyles(style)(UserProfile);
