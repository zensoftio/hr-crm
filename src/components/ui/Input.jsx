import React, { Component } from 'react';
import TextField from '@material-ui/core/TextField';

class Input extends Component {
    render() {
        return(
            <TextField
                id="full-width"
                label={this.props.label}
                InputLabelProps={{
                    shrink: true,
                }}
                placeholder={this.props.placeholder}
                margin="normal"
                defaultValue={this.props.value}
            />
        )
    }
}

export default Input;  