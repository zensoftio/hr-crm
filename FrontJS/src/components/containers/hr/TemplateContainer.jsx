import React, { Component } from 'react';
import { withStyles } from '@material-ui/core/styles';
import { TEMPLATES_URL } from '../../../utils/urls';
import { FetchDataAPI } from '../../../services/FetchDataAPI';
import {Input, TextField, Button, Divider } from '@material-ui/core';
//import Upload from '@material-ui-upload/Upload';
import { PutDataAPI } from '../../../services/PutDataAPI';
import { DeleteDataAPI } from '../../../services/DeleteDataAPI';
import { Redirect } from 'react-router-dom';

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


class TemplateContainer extends Component {
  constructor(props) {
    super(props)
    this.state = {
      subject: "",
      body: "",
      attachment: [],
    }
  }

  handleChange = (e) => {
    this.setState({
      [e.target.name]: e.target.value
    });
  }

  updateTemplate = () => {
    let data = {
      "subject": this.state.subject,
      "body": this.state.body,
      "attachment": this.state.attachment,
    };
    const URL = TEMPLATES_URL + "/" + this.props.templateId;
    PutDataAPI(URL,data);
    window.location.href = "/list_of_templates";
  }


  deleteTemplate = () => {
    const URL = TEMPLATES_URL + "/" + this.props.templateId;
    DeleteDataAPI(URL);
    window.location.href = "/list_of_templates";
  }

  handleUpload = (e) => {
    this.setState({
      [e.target.name]: e.target.files
    })
  }

  componentDidMount(){
    FetchDataAPI(TEMPLATES_URL + "/" + this.props.templateId)
      .then(template => this.setState({
        subject: template.subject,
        body: template.body,
        attachment: template.attachment
      }))
  }
  render(){
      const { classes } = this.props;
      const {
        subject,
        body,
        attachment,
      } = this.state;
      return(
        <div style={{ margin: " 0 1em"}}>
            <div className={classes.root}>
                Subject:
                <span className={classes.box}><TextField multiline name="subject" onChange={(e) => this.handleChange(e)} value={subject} placeholder="Input Subject name" /></span>
            </div>
            <div className={classes.root}>
                Body:
                <span className={classes.box}><TextField multiline value={body} name="body" onChange={(e) => this.handleChange(e)} placeholder="gujghj" /></span>
            </div>
            <div className={classes.root}>
                Attachment:
                <input
                  type="file"
                  multiple
                  name="attachment"
                  onChange={(e) => this.handleUpload(e)}
                  />
            </div>
            <Button onClick = { this.updateTemplate }>Update</Button>
            <Button onClick = { this.deleteTemplate }>Delete</Button>

        </div>
      )
  }


}
export default withStyles(style)(TemplateContainer);
