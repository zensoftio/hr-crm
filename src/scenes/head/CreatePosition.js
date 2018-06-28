import React from 'react';
import Header from '../general/Header';
import CreatePositionContainer from '../../components/containers/head/CreatePositionContainer'
const CreatePosition = () => {
    return (
        <div>
            <Header title="Создать Позицию"/>
          
           <CreatePositionContainer/>
        </div>
    )
}

export default CreatePosition;