import React, { Component } from 'react';
import ButtonSubmit from '../../ui/ButtonSubmit';
import TextArea from '../../ui/TextArea';
import SelectAutocomplete from '../../ui/SelectAutocomplete';

class CreatePositionContainer extends Component {
    render() {
        return(
            <div>
                <SelectAutocomplete />
                <TextArea />
                <ButtonSubmit>отправить</ButtonSubmit>
            </div>        
        )
    }
}

export default CreatePositionContainer;