import React from "react";
import TableList from "../../ui/Table";
import {Link} from 'react-router-dom';

const ListOfCandidates = () => {

    function MakeLinked(element, link) {
        return <Link to={link}>{element} </Link>;
    }

    const data = [
        ['Islam Akylbek uulu', 'JavaScript', 'Aктивный', '24/07/18', 'Comments is here...', MakeLinked('CV', 'CVLink'), MakeLinked('Открыть', 'profile')],
        ['Name surname', 'Python', 'Aктивный', '24/07/18', 'Comments is here...', MakeLinked('CV', 'CVLink'), MakeLinked('Открыть', 'profile')],
        ['Human human', 'Java', 'Aктивный', '24/07/18', 'Comments is here...', MakeLinked('CV', 'CVLink'), MakeLinked('Открыть', 'profile')],
    ];

    const header = ['№', 'Ф.И.О', 'ЯЗЫК', 'СТАТУС', 'ДАТА', 'КОММЕНТАРИЙ', 'ВЛОЖЕНИЕ', 'ПРОФИЛЬ'];

    return (<TableList header={header} data={data}/>);
}


export default ListOfCandidates;
