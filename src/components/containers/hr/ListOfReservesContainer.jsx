import React from "react";
import TableList from "../../ui/Table";
import {Link} from "react-router-dom";

const ListOfReserves = (props) => {

    function MakeLinked(element, link) {
        return <Link to={link}>{element} </Link>;
    }

    const data = [
        ['Islam Akylbek uulu', 'JavaScript', 'asdf@gmail.com', 'Comments is here...', MakeLinked('CV', 'CVLink'), MakeLinked('Открыть', 'ProfileLink')],
        ['Name surname', 'Python', 'asdf@gmail.com', 'Comments is here...', MakeLinked('CV', 'CVLink'), MakeLinked('Открыть', 'ProfileLink')],
        ['Human human', 'Java', 'asdf@gmail.com', 'Comments is here...', MakeLinked('CV', 'CVLink'), MakeLinked('Открыть', 'ProfileLink')],
    ];

    const header = ['№', 'Ф.И.О', 'ЯЗЫК', 'EMAIL', 'КОММЕНТАРИЙ', 'ВЛОЖЕНИЕ', 'ПРОФИЛЬ'];

    return (<TableList header={header} data={data}/>);
}

export default ListOfReserves;