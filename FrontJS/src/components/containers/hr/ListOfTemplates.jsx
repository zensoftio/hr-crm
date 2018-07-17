import React, { Component } from "react";
import TableList from "../../ui/Table";
import makeLinked from '../../../utils/MakeLinked';
import getLink from '../../../utils/GetLink';
import { TEMPLATES_URL } from '../../../utils/urls';
import { FetchDataAPI } from "../../../services/FetchDataAPI";

const header = ['№','Название','Открыть']
class Templates extends Component {
  constructor(props) {
    super(props);
    this.state = {
      data:[]
    }
  }

  componentDidMount(){
    FetchDataAPI(TEMPLATES_URL)
      .then(res => res.map(
        template => ({
          id: template.id,
          subject: template.subject,
          body: template.body,
          attachment: template.attachment
        })
      )).then(data => this.setState({data}));
  }


  render(){
    const { data } = this.state;
    const templates = data.map(
      item => [
        item.subject,
        makeLinked('Открыть', getLink("template", item.id))
      ]
    );
    return (<TableList header={header} data={templates}/>)
  }
}
export default Templates;
