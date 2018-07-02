import React from "react";
import TableList from "../../ui/Table";
import {Link} from "react-router-dom";

const ListOfInterns = () => {

    function MakeLinked(element, link) {
        return <Link to={link}>{element} </Link>;
    }

    const data = [
        ['Islam Akylbek uulu', 'JavaScript', 'Aктивный', '24/07/18', 'Comments is here...', MakeLinked('CV', 'CVlink')],
        ['Name surname', 'Python', 'Aктивный', '24/07/18', 'Comments is here...', MakeLinked('CV', 'CVlink')],
        ['Human human', 'Java', 'Aктивный', '24/07/18', 'Comments is here...', MakeLinked('CV', 'CVlink')],

    ];

    const header = ['№', 'Ф.И.О', 'ЯЗЫК', 'СТАТУС', 'ДАТА', 'КОММЕНТАРИЙ', 'ВЛОЖЕНИЕ'];

    return (<TableList header={header} data={data}/>);
}


export default ListOfInterns;
