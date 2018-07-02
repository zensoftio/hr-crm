import React from "react";
import TableList from "../../ui/Table";
import {Link} from "react-router-dom";

const ListOfNotifications = (props) => {

    function MakeLinked(element, link) {
        return <Link to={link}>{element} </Link>;
    }

    const data = [
        ['asdf@gmail.com', 'Comments is here...', MakeLinked('CV', 'CVlink')],
        ['asdf@gmail.com', 'Comments is here...', MakeLinked('CV', 'CVlink')],
        ['asdf@gmail.com', 'Comments is here...', MakeLinked('CV', 'CVlink')],
    ];

    const header = ['№', 'EMAIL', 'ЗАГОЛОВОК', 'ВЛОЖЕНИЕ'];

    return (<TableList header={header} data={data}/>);
}

export default ListOfNotifications;