import React from "react";
import TableList from "../../ui/Table";
import Button from '../../ui/ButtonSubmit';

const ListOfPositions = (props) => {

    const data = [
        ['Name', 'JavaScript', '12/12/2017', 3, <Button>Создать</Button>],
        ['Name', 'Python', '18/03/2017', 2, <Button>Создать</Button>],
        ['Name', 'Java', '21/04/2017', 4, <Button>Создать</Button>],
    ];

    const header = ['№', 'Ф.И.О', 'НАЗВАНИЕ', 'ДАТА', 'КОЛ-ВО', 'ДЕЙСТВИЕ'];

    return (<TableList header={header} data={data}/>);
}

export default ListOfPositions;