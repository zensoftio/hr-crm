import React, { Component } from 'react';
import { withStyles } from '@material-ui/core/styles';
import {Input, TextField, Button, Divider } from '@material-ui/core';
import { Checkbox, ListItemText, Select, MenuItem } from '@material-ui/core';
import AddIcon from '@material-ui/icons/Add'
import ModalButton from '../../ui/ModalWindow';
import { PostDataAPI } from '../../../services/PostDataAPI';
import MaskedInput from 'react-text-mask';
import PropTypes from 'prop-types';

let error = "";
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
      let count = 0;
      for(let i = 0;i < this.state.phone.length;i++){
        count += (this.state.phone[i].length === 16) ? 1 : 0;
      }
      let isOk = false;
      const begin_time = Date.parse(this.state.begin_time);
      isOk = (!isNaN(begin_time) && count === this.state.phone.length) ? true : false;
      error = (!isOk) ? "Please, fill all fields." : "";
      if(isOk){
        this.state.begin_time += ":00+06:00"
        this.state.end_time = this.state.begin_time;
      }
      return isOk
      // const URL = 'http://159.65.153.5/api/v1/interviews';
      // PostDataAPI(URL, this.state);
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
            <Checkbox checked={this.state.email_heads.indexOf(item) > -1}/>
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
                          {error}
                            <div className={classes.root}>
                               Дата:
                               <span className={classes.box}>
                               <TextField  onChange={this.handleChange} name="begin_time" id="datetime-local" label="Next appointment" type="datetime-local" defaultValue={"2017-05-21,07:00"} className={classes.textField} InputLabelProps={{shrink: true}}/>
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
                                    <Input onChange={this.handleChangePhoneNumber} defaultValue={'996 5'} name={index} id="formatted-text-mask-input" inputComponent={TextMaskCustom} required />
                                  </span>
                                ))}
                                <span className={classes.box}><Button type="button" onClick={this.handleAddInputForPhoneNumber} variant="fab" aria-label="add" mini><AddIcon/></Button></span>
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
