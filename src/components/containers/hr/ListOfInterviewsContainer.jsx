import React from "react";
import TableList from "../../ui/Table";

const ListOfInterviews = (props) => {

    const data = [
        ['Islam Akylbek uulu', '29/06/18', 'Junior JS Developer', 'Name Surname', 'EditLink', 'DeleteLink'],
        ['Name surname', '29/06/18', 'Middle JS Developer', 'Name2 Surname', 'EditLink', 'DeleteLink'],
        ['Human human', '29/06/18', 'Senior JS Developer', 'Name3 Surname', 'EditLink', 'DeleteLink'],
    ];

    const header = ['№', 'Ф.И.О', 'ДАТА', 'ПОЗИЦИЯ', 'ИНТЕРВЬЮЕР', 'ДЕЙСТВИЯ', 'ДЕЙСТВИЯ'];

    return (<TableList header={header} data={data}/>);
}

export default ListOfInterviews;