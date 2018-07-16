import React, { Component } from "react";
import TableList from "../../ui/Table";
import makeLinked from '../../../utils/MakeLinked';
import getLink from '../../../utils/GetLink';

const TempList = [
  {"subject": "Name1","id": 1},
  {"subject": "Name2","id": 2},
  {"subject": "Name3","id": 3},
  {"subject": "Name4","id": 4},
];

const header = ['№','Название','Открыть']
class Templates extends Component {
  constructor(props) {
    super(props);
  }
  render(){
    const data = TempList.map(
      item => [
        item.subject,
        makeLinked('Открыть', getLink("template", item.id))
      ]
    );
    return(<TableList header={header} data={data}/>);
  }
}
export default Templates;
