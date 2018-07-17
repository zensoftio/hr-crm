import React from 'react';
import Header from '../general/Header';
import ListOfTemplates from '../../components/containers/hr/ListOfTemplates';

const ListTemplates = () => {
    return (
        <div>
            <Header title="Список Шаблонов"/>

            <ListOfTemplates/>
        </div>
    )
}

export default ListTemplates;
