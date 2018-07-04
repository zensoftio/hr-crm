import React from "react";
import TableList from "../../ui/Table";
import {Link} from "react-router-dom";

const ListOfInterviews = (props) => {

    function MakeLinked(element, link) {
        return <Link to={link}>{element} </Link>;
    }

    const data = [
        ['Islam Akylbek uulu', '29/06/18', 'Junior JS Developer', 'Name Surname', MakeLinked('Изменить', 'edit_interview'), MakeLinked('Удалить', 'DeleteLink')],
        ['Name surname', '29/06/18', 'Middle JS Developer', 'Name2 Surname', MakeLinked('Изменить', 'edit_interview'), MakeLinked('Удалить', 'DeleteLink')],
        ['Human human', '29/06/18', 'Senior JS Developer', 'Name3 Surname', MakeLinked('Изменить', 'edit_interview'), MakeLinked('Удалить', 'DeleteLink')],
    ];

    const header = ['№', 'Ф.И.О', 'ДАТА', 'ПОЗИЦИЯ', 'ИНТЕРВЬЮЕР', 'ДЕЙСТВИЯ', 'ДЕЙСТВИЯ'];

    return (<TableList header={header} data={data}/>);
}

export default ListOfInterviews;