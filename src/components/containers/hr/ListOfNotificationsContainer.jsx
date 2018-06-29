import React from "react";
import TableList from "../../ui/Table";

const ListOfNotifications = (props) => {

    const data = [
        ['asdf@gmail.com', 'Comments is here...', 'CVlink'],
        ['asdf@gmail.com', 'Comments is here...', 'CVlink'],
        ['asdf@gmail.com', 'Comments is here...', 'CVlink'],
    ];

    const header = ['№', 'EMAIL', 'ЗАГОЛОВОК', 'ВЛОЖЕНИЕ'];

    return (<TableList header={header} data={data}/>);
}

export default ListOfNotifications;