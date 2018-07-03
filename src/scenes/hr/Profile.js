import React, { Component } from 'react';
import { withStyles } from '@material-ui/core/styles';
import Header from '../general/Header';
import Input from '../../components/ui/Input';
import Select from '../../components/ui/SelectList';
import Paper from '@material-ui/core/Paper';
import TextArea from '../../components/ui/TextArea';
import Button from '../../components/ui/ButtonSubmit';
import Divider from '@material-ui/core/Divider';
import { Document } from 'react-pdf/dist/entry.webpack';

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
    ];
 
const style = {
    box: {
        margin: '1em .5em'
    },
    paperBox: {
        margin: '1em 1.5em',
        padding: '1.5em 1em'
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
                    <div>
                        <span className={classes.box}><Button>Пригласить на интервью</Button></span>
                        <span className={classes.box}><Button>Нанять</Button></span>
                        <span className={classes.box}><Button>Удалить</Button></span>
                        <span className={classes.box}><Button>Сохранить</Button></span>
                        <span className={classes.box}><Button>Отправить ТЗ</Button></span>
                        <span className={classes.box}><Button>Отправить письмо</Button></span>
                    </div>
                </Paper>
            </div>
        )
    }
}

export default withStyles(style)(UserProfile);
