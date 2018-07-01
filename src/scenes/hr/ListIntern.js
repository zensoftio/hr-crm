import React from 'react';
import Header from '../general/Header';
import ListOfInterns from '../../components/containers/hr/ListOfInternsContainer';

const ListInterns = () => {
    return (
        <div>
            <Header title="Список стажеров"/>

            <ListOfInterns/>
        </div>
    )
}

export default ListInterns;

