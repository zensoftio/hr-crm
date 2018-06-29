import React from "react";
import TableList from "../../ui/Table";

const ListOfReserves = (props) => {

    const data = [
        ['Islam Akylbek uulu', 'JavaScript', 'asdf@gmail.com', 'Comments is here...', 'CVlink', 'profileLink'],
        ['Name surname', 'Python', 'asdf@gmail.com', 'Comments is here...', 'CVlink', 'profileLink'],
        ['Human human', 'Java', 'asdf@gmail.com', 'Comments is here...', 'CVlink', 'profileLink'],
    ];

    const header = ['№', 'Ф.И.О', 'ЯЗЫК', 'EMAIL', 'КОММЕНТАРИЙ', 'ВЛОЖЕНИЕ', 'ПРОФИЛЬ'];

    return (<TableList header={header} data={data}/>);
}

export default ListOfReserves;