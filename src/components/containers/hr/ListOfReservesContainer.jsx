import React from "react";
import TableList from "../../ui/Table";

const ListOfReserves = (props) => {


    const data = [
        ['Islam Akylbek uulu', 'JavaScript', 'asdf@gmail.com', 'Comments is here...', 'CVlink', 'profileLink'],
        ['Name surname', 'Python', 'asdf@gmail.com', 'Comments is here...', 'CVlink', 'profileLink'],
        ['Human human', 'Java', 'asdf@gmail.com', 'Comments is here...', 'CVlink', 'profileLink'],
        // createData('Islam Akylbek uulu', 'JavaScript', 'Aктивный', '24/07/18', 'Comments is here...', 'CV'),
        // createData('Name surname', 'Python', 'Aктивный', '24/07/18', 'Comments is here...', 'CV'),
        // createData('Human human', 'Java', 'Aктивный', '24/07/18', 'Comments is here...', 'CV'),

    ];

    const header = ['№','Ф.И.О', 'ЯЗЫК', 'EMAIL', 'КОММЕНТАРИЙ', 'ВЛОЖЕНИЕ', 'ПРОФИЛЬ'];

    return (<TableList header={header} data={data}/>);
}

// function createData(name, language, status, date, comment, attachment) {
//     return {name, language, status, date, comment, attachment};
// }

export default ListOfReserves;