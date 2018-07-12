import React, { Component } from 'react';
import { withStyles } from '@material-ui/core/styles';
import { TextField, Button, Divider } from '@material-ui/core';
import Select from '../../ui/Select';
import ModalButton from '../../ui/ModalWindow';

const Department = [
    "Developer",
],
    Experience = [
        "2 года"
    ],
    Level = [
        "Junior",
    ],
    Status = [
        "активен",
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
    ];
 
const style = {
    root: {
        display: 'flex',
        justifyvalue: 'flex-start',
        alignItems: 'center',
        margin: "1em 0"
    },
    box: {
        margin: '0 1em'
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

let today = new Date();
let dd = today.getDate();
let mm = today.getMonth() + 1; //January is 0!

let yyyy = today.getFullYear();

    if(dd<10){
        dd='0'+dd;
    } 
    if(mm<10){
        mm='0'+mm;
    } 

const now = yyyy + '-' + mm + '-' + dd;

class UserProfile extends Component {

    constructor(props){
        super(props)
        this.state = {
            comments: [
                {
                    created: '04/05/2018',
                    text: "Тестовый коммент 1"
                },
            ]
        }
    }

    handleComment = (e) => {
        this.setState({
            comments: [
                {
                    created: now,
                    text: e.target.value
                }
            ]
        })
    }

    createComment = (e) => {
        this.setState({
            comments: [
                {
                    created: now,
                    text: e.target.value
                }
            ]
        })
    }

    RenderComment = (value) => {
        return value.map((item, index) => (
            <div style={{ margin: "1.5em 0" }}><em className={style.box} key={index}>{ item.created } - {item.text}</em></div>
        ))
    }

    render() { 
        const { classes } = this.props;
        const { comments } = this.state;

        return (
            <div style={{ margin: " 0 1em"}}>
                <div className={classes.root}>
                    Фамилия: 
                    <span className={classes.box}><TextField defaultValue="Пупкин" placeholder="введите фамилию" /></span>
                </div>
                <div className={classes.root}>
                    Имя: 
                    <span className={classes.box}><TextField defaultValue="Вася" placeholder="введите имя" /></span>
                </div>
                <div className={classes.root}>
                    Email: 
                    <span className={classes.box}><TextField defaultValue="example@gmail.com" placeholder="введите email" /></span>
                </div>
                <div className={classes.root}>
                    Номер: 
                    <span className={classes.box}><TextField defaultValue="+996555000000" placeholder="введите номер" /></span>
                </div>
                <div className={classes.root}>
                    Skype: 
                    <span className={classes.box}><TextField defaultValue="vasya.pupkin" placeholder="введите адрес" /></span>
                </div>
                <div className={classes.root}>
                    Отдел: 
                    <span className={classes.box}><TextField defaultValue={Department}/></span>
                </div>
                <div className={classes.root}>
                    Опыт: 
                    <span className={classes.box}><TextField defaultValue={Experience}/></span>
                </div>
                <div className={classes.root}>
                    Уровень: 
                    <span className={classes.box}><TextField defaultValue={Level}/></span>
                </div>
                <div className={classes.root}>
                    Статус: 
                    <span className={classes.box}><TextField defaultValue={Status}/></span>
                </div>
                <div className={classes.root}>
                    Резюме:
                    <span className={classes.box}><a href=""> CV.pdf </a></span>
                </div>
                <div>
                    <div className={classes.root}><span style={{ color: 'blue' }}>Комментарии</span></div> 
                    { this.RenderComment(comments) }
                </div>
                <div className={classes.root}>
                    <TextField multiline onChange={this.handleComment} placeholder="комментарий..." />
                    <span className={classes.box }><Button variant="contained" onClick={this.createComment} >комментировать</Button></span>
                </div>
                <div className={classes.root}>
                    <Divider />
                </div>
                <div className={classes.root}>
                <ModalButton
                    title="Заполните все поля"
                    text={
                        <div>
                            <div className={classes.root}>
                               Дата: 
                               <span className={classes.box}>
                                    <TextField type="date" defaultValue={now.toString()} />
                               </span> 
                            </div>
                            <div className={classes.root}>
                                Время:
                                <span className={classes.box}>
                                    <TextField defaultValue="07:30" type="time" />
                                </span>
                            </div>
                            <div className={classes.root}>
                                Интервьювер:
                                <span className={classes.box}><Select optionValue={Interviewers}/></span>
                            </div>
                            <div className={classes.root}>
                                Место:
                                <span className={classes.box}><TextField placeholder="место проведения"/></span>
                            </div>
                            <div className={classes.root}>
                                Сообщение:
                                <span className={classes.box}><TextField multiline placeholder="введите сообщение" /></span>
                            </div>
                        </div> 
                    }
                    width="500"
                    rightBtn="отправить"
                    leftBtn="закрыть"
                    >Пригласить на интервью</ModalButton>
                    <ModalButton>Нанять</ModalButton>
                    <ModalButton
                        title="Вы действительно хотите удалить?"
                        leftBtn="ДА"
                        rightBtn="НЕТ"
                    >Удалить
                    </ModalButton>
                    <ModalButton>Сохранить</ModalButton>
                    <ModalButton
                        title="Отправить тестовое задание"
                        text={
                            <div>
                                <div className={classes.root}>Тема:
                                    <span className={classes.box}><Select optionValue={TopicTemp}/></span>
                                </div>
                                <div className={classes.root}>Сообщение:
                                    <span className={classes.box}><TextField multiline value={MsgTemp}/></span>
                                </div>
                                <div className={classes.root}>Ссылка на задание:
                                     <span className={classes.box}><a href="">document.docx</a></span>
                                </div>
                            </div>
                        }
                        leftBtn="закрыть"
                        rightBtn="отправить"
                    >Отправить ТЗ</ModalButton>
                    <ModalButton
                        title="Отправить сообщение"
                        text={
                            <div>
                                <div className={classes.root}>Тема:
                                    <span className={classes.box}><TextField placeholder="тема сообщения"/></span>
                                </div>
                                <div className={classes.root}>Сообщение:
                                    <span className={classes.box}><TextField multiline value={MsgTemp}/></span>
                                </div>
                            </div>
                        }
                        leftBtn="закрыть"
                        rightBtn="отправить"
                    >Отправить письмо</ModalButton>
                </div>
            </div>
        )
    }
}

export default withStyles(style)(UserProfile);
