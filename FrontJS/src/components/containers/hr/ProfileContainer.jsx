import React, { Component } from 'react';
import { withStyles } from '@material-ui/core/styles';
import {Input, TextField, Button, Divider } from '@material-ui/core';
import { Checkbox, ListItem, ListItemText,Paper, Select, MenuItem } from '@material-ui/core';
import AddIcon from '@material-ui/icons/Add'
import ModalButton from '../../ui/ModalWindow';
import { PostDataAPI } from '../../../services/PostDataAPI';
import MaskedInput from 'react-text-mask';
import PropTypes from 'prop-types';
import { FetchDataAPI } from '../../../services/FetchDataAPI';
import { CANDIDATES_URL, INTERVIEWS_URL, USERS_URL } from '../../../utils/urls';
import DateConvert from '../../../utils/DateConvert';
import getStatus from '../../../utils/GetStatus';


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
function isPossibleToSend(json){

  let isOk = true;
  const begin_time = Date.parse(json.begin_time);
  isOk = (!isNaN(begin_time)) ? true : false;
  isOk = (json.candidate !== 0 && isOk) ? true : false;
  isOk = (json.description.length > 0 && isOk) ? true : false;
  isOk = (json.location.length > 0 && isOk) ? true : false;
  isOk = (json.interviewers.length > 0 && isOk) ? true : false;
  error = (isOk) ? "" : "Please, fill all fields.";
  return isOk
}
let Heads = [],
    email_heads = [],

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
    },
    commentBox: {
        width: '100%'
    }
}
function TextMaskCustom(props){
  const { inputRef, ...other } = props;

  return (
    <MaskedInput
      {...other}
      ref={inputRef}
      mask={['(', /[9]/, /[9]/, /[6]/, ')', ' ', /\d/, /\d/, /\d/, '-', /\d/, /\d/, /\d/, /\d/, /\d/, /\d/]}
      placeholderChar={'\u2000'}
      showMask
    />
  );
}

TextMaskCustom.propTypes = {
  inputRef: PropTypes.func.isRequired,
};
async function getUsers(){
  const users = await FetchDataAPI(USERS_URL);
  for(let i = 0;i < users.results.length;i++){
    Heads[i] = users.results[i];
    email_heads[i] = users.results[i].email
  }
}
getUsers();

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
let error = "";

class UserProfile extends Component {

    constructor(props){
        super(props)
        this.state = {
            first_name: "",
            last_name: "",
            email: "",
            candidate_phone: "",
            skype: "",
            department: "",
            position: "",
            experience: 0,
            level: "",
            cv: "",
            comments: [],
            begin_time: "",
            description: "",
            location: "",
            interviewers: [],
            phone: [],
            status: "TO_BE_CONDUCTED"
        }
    }

    componentDidMount() {
        FetchDataAPI(CANDIDATES_URL + "/" + this.props.profileId)
            .then(candidate => this.setState({
                first_name: candidate.first_name,
                last_name: candidate.last_name,
                email: candidate.email,
                candidate_phone: candidate.phone,
                experience: candidate.experience,
                level: candidate.level,
                cv: candidate.CVs,
                status: candidate.status,
                skype: candidate.skype,
                position: candidate.position.name,
                department: candidate.position.department.name,
                interviews: candidate.interviews,
                comments: candidate.comments
            }))
    }

    handleChange = (event) => {
      this.setState({
        [event.target.name]: event.target.value
      });
    }

    handleChangeForTime = (event) => {
      const begin_time = event.target.value + ":00+06:00";
      this.setState({
        begin_time: begin_time,
        candidate: this.props.profileId
      })
    }

    handleSubmit = (event) => {
      let jsonObj = {
        'candidate': this.state.candidate,
        'begin_time': this.state.begin_time,
        'interviewers': [],
        'description': this.state.description,
        'location': this.state.location,
        'phone': this.state.phone,
        'status': 'TO_BE_CONDUCTED'
      }
      for(let i = 0;i < Heads.length;i++){
        if(Heads[i].email === this.state.interviewers[i]){
          jsonObj.interviewers.push(Heads[i].id);
        }
      }
      const isOk = isPossibleToSend(jsonObj);
      if(isOk){
        PostDataAPI(INTERVIEWS_URL, jsonObj);
      }
      return isOk;
    }

    handleChangePhoneNumber = (event) => {
      this.state.phone[event.target.name] = event.target.value;
      console.log(this.state);
    }

    handleAddInputForPhoneNumber = () => {
      let phone = this.state.phone.concat([''])
      this.setState({
        phone
      })
    }
    RenderMultipleSelectItem = (props) => {
      return props.map((item, index) => (
        <MenuItem key={index} value={item}>
            <Checkbox checked={this.state.interviewers.indexOf(item) > -1}/>
            <ListItemText primary={item} />
        </MenuItem>
      ))
    }

    render() {
        const { classes } = this.props;
        const { first_name,
                last_name,
                email,
                candidate_phone,
                skype,
                department,
                position,
                experience,
                level,
                status,
                cv,
                comments,
                interviewers,
                location,
                description } = this.state;

        return (
            <div style={{ margin: " 0 1em"}}>
                <div className={classes.root}>
                    Фамилия:
                    <span className={classes.box}><TextField value={first_name} placeholder="введите фамилию" /></span>
                </div>
                <div className={classes.root}>
                    Имя:
                    <span className={classes.box}><TextField value={last_name} placeholder="введите имя" /></span>
                </div>
                <div className={classes.root}>
                    Email:
                    <span className={classes.box}><TextField value={email} placeholder="введите email" /></span>
                </div>
                <div className={classes.root}>
                    Номер:
                    <span className={classes.box}><TextField value={candidate_phone} placeholder="введите номер" /></span>
                </div>
                <div className={classes.root}>
                    Skype:
                    <span className={classes.box}><TextField value={skype} placeholder="введите адрес" /></span>
                </div>
                <div className={classes.root}>
                    Отдел:
                    <span className={classes.box}><TextField value={department}/></span>
                </div>
                <div className={classes.root}>
                    Позиция:
                    <span className={classes.box}><TextField value={position}/></span>
                </div>
                <div className={classes.root}>
                    Опыт:
                    <span className={classes.box}><TextField type="number" value={experience}/></span>
                </div>
                <div className={classes.root}>
                    Уровень:
                    <span className={classes.box}><TextField value={level}/></span>
                </div>
                <div className={classes.root}>
                    Статус:
                    <span className={classes.box}><TextField value={getStatus(status)}/></span>
                </div>
                <div className={classes.root}>
                    Резюме:
                    <span className={classes.box}><a href={ cv.length !== 0 ? cv[0].url : "example-link.com" }> Ссылка на резюме </a></span>
                </div>
                <div className={classes.root}>
                    <Paper className={classes.commentBox}>
                        {
                            comments.map((item, index) => {
                                return (
                                    <ListItem key={index}>
                                        <ListItemText>автор: {item.created_by.first_name + " " + item.created_by.last_name} <br/>
                                            текст: {item.text} <br/>
                                            создан: {DateConvert(item.created)}
                                         </ListItemText>
                                    </ListItem>
                                )
                            })
                        }
                    </Paper>
                </div>
                <div className={classes.root}>
                    <TextField multiline name="newComment" onChange={this.handleChange} placeholder="комментарий..." />
                    <span className={classes.box }><Button variant="contained" onClick={() => alert("It will be soon...")} >комментировать</Button></span>
                </div>
                <div className={classes.root}>
                    <Divider />
                </div>
                <div className={classes.root}>
                <ModalButton onClick={this.handleSubmit}
                    title="Заполните все поля"
                    text={
                        <div>
                          {error}
                            <div className={classes.root}>
                               Дата:
                               <span className={classes.box}>
                               <TextField  onChange={this.handleChangeForTime} name="begin_time" id="datetime-local" label="Next appointment" type="datetime-local" defaultValue={"2017-05-21,07:00"} className={classes.textField} InputLabelProps={{shrink: true}}/>
                               </span>
                            </div>
                            <div className={classes.root}>
                                HoD:
                                <span className={classes.box}>
                                 <Select multiple name="interviewers" value={interviewers} onChange={this.handleChange} renderValue={selected => selected.join(', ')} MenuProps={MenuProps} required>
                                   {this.RenderMultipleSelectItem(email_heads)}
                                 </Select>
                                </span>
                            </div>
                            <div className={classes.root}>
                                Номер телефона:
                                {this.state.phone.map((phone, index) => (
                                  <span key={index}>
                                    <Input onChange={this.handleChangePhoneNumber} defaultValue={'996 5'} name={index} id="formatted-text-mask-input" inputComponent={TextMaskCustom} required />
                                  </span>
                                ))}
                                <span className={classes.box}><Button type="button" onClick={this.handleAddInputForPhoneNumber} variant="fab" aria-label="add" mini><AddIcon/></Button></span>
                            </div>
                            <div className={classes.root}>
                                Место:
                                <span className={classes.box}><TextField onChange={this.handleChange} name="location" value={location} placeholder="место проведения" required/></span>
                            </div>
                            <div className={classes.root}>
                                Сообщение:
                                <span className={classes.box}><TextField onChange={this.handleChange} name="description" value={description} multiline placeholder="введите сообщение" required/></span>
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
                                    <span className={classes.box}><Select value={TopicTemp}/></span>
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
