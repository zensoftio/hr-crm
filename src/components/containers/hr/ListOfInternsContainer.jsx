import React from "react";
import TableList from "../../ui/Table";

const ListOfInterns = (props) => {

    const data = [
        ['Islam Akylbek uulu', 'JavaScript', 'Aктивный', '24/07/18', 'Comments is here...', 'CVlink'],
        ['Name surname', 'Python', 'Aктивный', '24/07/18', 'Comments is here...', 'CVlink'],
        ['Human human', 'Java', 'Aктивный', '24/07/18', 'Comments is here...', 'CVlink'],

    ];

    const header = ['№', 'Ф.И.О', 'ЯЗЫК', 'СТАТУС', 'ДАТА', 'КОММЕНТАРИЙ', 'ВЛОЖЕНИЕ'];

    return (<TableList header={header} data={data}/>);
}


export default ListOfInterns;
