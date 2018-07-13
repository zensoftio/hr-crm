import React from 'react';
import Header from '../general/Header';
import ListOfCandidates from '../../components/containers/hr/ListOfCandidatesContainer';

const ListCandidates = () => {
    return (
        <div>
            <Header title="Список кандидатов"/>

            <ListOfCandidates/>
        </div>
    )
}

export default ListCandidates;

