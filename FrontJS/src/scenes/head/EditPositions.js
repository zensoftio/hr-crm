import React from 'react';
import Header from '../general/Header';
import EditPositionContainer from '../../components/containers/head/EditPositionContainer';


const EditPositions = (props) => {
    return (
			
      <div>
        <Header title="Редактировать Позицию"/>     
        <EditPositionContainer position_id={props.match.params.id}/>
      </div>
    )
}

export default EditPositions;