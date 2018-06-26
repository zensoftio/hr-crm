import React from 'react';
import FormInput from '../ui-kit/FormInput';
import { observable, action, observer } from 'mobx';
import positionService from '../../services/position';

@observer
export default class CreatePositionContainer extends React.Component {

    @observable
    quantity = 0

    submitHandler = () => {
        const data = {};
        positionService.create(data).then();
    }

    render () {
        return (
            <div>
                <FormInput title='Department'>
                    <select >
                        <option name=""/>
                    </select>
                </FormInput>
                <FormInput title='Quantity'>
                   <input type="text" value={this.quantity} onChange={action(event => this.quantity = event.target.value)}/>
                </FormInput>
                <button onClick={this.submitHandler}>asdf</button>
            </div>
        )
    }

}