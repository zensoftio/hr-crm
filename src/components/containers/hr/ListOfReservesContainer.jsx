import React, { Component } from 'react';
import { withStyles } from '@material-ui/core/styles';
import {Input, TextField, Button, Divider } from '@material-ui/core';
import { Checkbox, ListItemText,Paper, Select, MenuItem } from '@material-ui/core';
import { FetchDataAPI } from '../../../services/FetchDataAPI';
import { CANDIDATES_URL } from '../../../utils/urls';
import DateConvert from '../../../utils/DateConvert';
import RenderSelectItem from '../../../utils/RenderSelectItem';
import getStatus from '../../../utils/GetStatus';

const styles = ({
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
})

class EditInterview extends Component {

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

        handleSubmit = (event) => {
            let count = 0;
            for(let i = 0;i < this.state.phone.length;i++){
                count += (this.state.phone[i].length === 16) ? 1 : 0;
            }
        }

        RenderMultipleSelectItem = (props) => {
            return props.map((item, index) => (
                <MenuItem key={index} value={item}>
                    <Checkbox checked={this.state.email_heads}/>
                    <ListItemText primary={item} />
                </MenuItem>
            ))
        }

        render() {
            const { classes } = this.props;
            const { first_name } = this.state;

            return (
                <div style={{ margin: " 0 1em"}}>
                    <div className={classes.root}>
                        Фамилия:
                        <span className={classes.box}><TextField value={first_name} placeholder="введите фамилию" /></span>
                    </div>
                </div>
            )
        }
}

export default withStyles(styles)(EditInterview);
