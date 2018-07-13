import React, { Component } from 'react';
import { withStyles } from '@material-ui/core/styles';
import { Input, TextField, Button, Divider } from '@material-ui/core';
import { Checkbox, ListItemText, Select, MenuItem } from '@material-ui/core';
import ModalButton from '../../ui/ModalWindow';
import { PostDataAPI } from '../../../services/PostDataAPI';


const ITEM_HEIGHT = 48;
const ITEM_PADDING_TOP = 8;
const MenuProps = {
  PaperProps: {
    style: {
      maxHeight: ITEM_HEIGHT * 4.5 + ITEM_PADDING_TOP,
      width: 250,
    },
  },
}

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
        'Имя Фамилия1',
        'Имя Фамилия2',
        'Имя Фамилия3'
    ],
    Heads = [
      'Head01',
      'Head02',
      'Head03'
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
        console.log(this.props)
        this.state = {
          begin_time: "",
          end_time: "",
          description: "",
          location: "",
          email_heads: [],
          email_interviewer: "",
          phone: []
        }
    }

    handleChange = (event) => {
      this.setState({
        [event.target.name]: event.target.value
      })
    }
    handleChangePhoneNumber = (event) => {
      this.state.phone[event.target.name] = event.target.value;
      console.log(this.state);
    }

    handleSubmit = (event) => {
      this.state.begin_time += "T" + this.state.end_time + ":00+06:00"
      this.state.end_time = this.state.begin_time;

      console.log("STATE")
      console.log(this.state)
      console.log("STATE")
      const URL = 'http://159.65.153.5/api/v1/interviews';
      PostDataAPI(URL, this.state);
    }
    handleAddInputForPhoneNumber = () => {

      let phone = this.state.phone.concat([''])
      this.setState({
        phone
      })
      // return props.map((item, index) => {
      //   <MenuItem key={index} value={item}>
      //     <TextField/>
      //     <ListItemText primary={item}/>
      //   </MenuItem>
      // })
    }

    RenderMultipleSelectItem = (props) => {
      return props.map((item, index) => (
        <MenuItem key={index} value={item}>
            <Checkbox/>
            <ListItemText primary={item} />
        </MenuItem>
      ))
    }
    RenderSelectItem = (props) => {
      return props.map((item, index) => (
          <MenuItem key={index} value={item}>{item}</MenuItem>
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

                <div className={classes.root}>
                    <TextField multiline onChange={this.handleComment} placeholder="комментарий..." />
                    <span className={classes.box }><Button variant="contained" onClick={this.createComment} >комментировать</Button></span>
                </div>
                <div className={classes.root}>
                    <Divider />
                </div>
                <div className={classes.root}>
                <ModalButton onClick={this.handleSubmit}
                    title="Заполните все поля"
                    text={
                        <div>
                            <div className={classes.root}>
                               Дата:
                               <span className={classes.box}>
                                    <Input type="date" onChange={this.handleChange} name="begin_time" placeholder={now.toString()} required/>
                               </span>
                            </div>
                            <div className={classes.root}>
                                Время:
                                <span className={classes.box}>
                                    <TextField type="time" name="end_time" onChange={this.handleChange} placeholder="07:30" required/>
                                </span>
                            </div>
                            <div className={classes.root}>
                                Интервьювер:
                                <span className={classes.box}><Select onChange={this.handleChange} name="email_interviewer" value={this.state.email_interviewer} required>{this.RenderSelectItem(Interviewers)}</Select> </span>
                            </div>
                            <div className={classes.root}>
                                HoD:
                                <span className={classes.box}>
                                 <Select multiple name="email_heads" value={this.state.email_heads} onChange={this.handleChange} renderValue={selected => selected.join(', ')} MenuProps={MenuProps} required>
                                   {this.RenderMultipleSelectItem(Heads)}
                                 </Select>
                                </span>
                            </div>
                            <div className={classes.root}>
                                Номер телефона:
                                {this.state.phone.map((phone, index) => (
                                  <span key={index}>
                                    <TextField onChange={this.handleChangePhoneNumber} name={index} placeholder="996*********" required />
                                  </span>
                                ))}
                                <span className={classes.box}><Button type="button" onClick={this.handleAddInputForPhoneNumber} variant="contained">+</Button></span>
                            </div>
                            <div className={classes.root}>
                                Место:
                                <span className={classes.box}><TextField onChange={this.handleChange} name="location" placeholder="место проведения" required/></span>
                            </div>
                            <div className={classes.root}>
                                Сообщение:
                                <span className={classes.box}><TextField onChange={this.handleChange} name="description" multiline placeholder="введите сообщение" required/></span>
                            </div>
                        </div>
                    }
                    width="500"
                    rightBtn="Отправить"
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
                                    <span className={classes.box}><Select defaultValue={TopicTemp}/></span>
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
