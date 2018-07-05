import React from "react";
import TableList from "../../ui/Table";
import Button from '../../ui/ButtonSubmit';


const ListOfVacancies = (props) => {

    const data = [
        ['JavaScript', '12/11/2017', '12/12/2017', 'опубликовано', <Button>открыть</Button>],
        ['Scala/JS', '18/02/2017', '18/03/2017', 'не опубликовано', <Button>открыть</Button>],
        ['Python', '21/05/2017', '21/04/2017', 'не опубликовано', <Button>открыть</Button>],
    ];

    const header = ['№', 'НАЗВАНИЕ', 'ДАТА СОЗДАНИЯ', 'ПОСЛЕДНЯЯ ПУБЛ.', 'СТАТУС', 'ДЕЙСТВИЕ'];

    return (<TableList header={header} data={data}/>);
}

export default ListOfVacancies;