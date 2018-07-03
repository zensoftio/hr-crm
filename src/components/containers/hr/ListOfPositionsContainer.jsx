import React from "react";
import TableList from "../../ui/Table";
import ModalDialog from '../../ui/ModalWindow';

const modal = [
    <ModalDialog
        title="Вакансия уже существует"
        text="Выберите действие"
        leftBtn="создать"
        rightBtn="просмотр"
    >
        Создать
    </ModalDialog>
];

const ListOfPositions = () => {

    const data = [
        ['Name', 'JavaScript', '12/12/2017', 3, modal],
        ['Name', 'Python', '18/03/2017', 2, modal],
        ['Name', 'Java', '21/04/2017', 4, modal],
    ];

    const header = ['№', 'Ф.И.О', 'НАЗВАНИЕ', 'ДАТА', 'КОЛ-ВО', 'ДЕЙСТВИЕ'];

    return (<TableList header={header} data={data}/>);
}

export default ListOfPositions;