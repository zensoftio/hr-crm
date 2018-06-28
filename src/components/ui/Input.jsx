import React, { Component } from 'react';
import { withStyles } from '@material-ui/core/styles';
import TextField from '@material-ui/core/TextField';

const styles = theme => ({
    input: {
        width: '100%'
    }
});

class Input extends Component {
    render() {
        const { classes } = this.props;
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
                className={classes.input}
            />
        )
    }
}

export default withStyles(styles) (Input);  