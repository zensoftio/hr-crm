import React, { Component } from 'react';
import { Select, MenuItem } from '@material-ui/core';

export default class SelectBox extends Component {
    constructor(props){
        super(props)
        this.state = {
            value: ''
        };
    }
    handleChange = (event) => {
        this.setState({ value: event.target.value });
    };

    RenderSelectItem = (props) => {
        return props.map((item, index) => (
            <MenuItem key={index} value={item}>{item}</MenuItem>
        ))
    }

    render() {
        const { value } = this.state;
        const { optionValue, name } = this.props;
        return (
            <Select name={name} value={value} onChange={this.handleChange} displayEmpty>
                <MenuItem disabled value=""><em>Выбрать</em></MenuItem>
                { this.RenderSelectItem(optionValue) }
            </Select>
        )
    }
}