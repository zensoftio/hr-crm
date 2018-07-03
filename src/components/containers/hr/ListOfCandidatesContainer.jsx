import React from "react";
import TableList from "../../ui/Table";

const ListOfCandidates = (props) => {

    const data = [
        ['Islam Akylbek uulu', 'JavaScript', 'Aктивный', '24/07/18', 'Comments is here...', 'CVlink', 'profileLink'],
        ['Name surname', 'Python', 'Aктивный', '24/07/18', 'Comments is here...', 'CVlink', 'profileLink'],
        ['Human human', 'Java', 'Aктивный', '24/07/18', 'Comments is here...', 'CVlink', 'profileLink'],

    ];

    const header = ['№', 'Ф.И.О', 'ЯЗЫК', 'СТАТУС', 'ДАТА', 'КОММЕНТАРИЙ', 'ВЛОЖЕНИЕ', 'ПРОФИЛЬ'];

    return (<TableList header={header} data={data}/>);
}


export default ListOfCandidates;
