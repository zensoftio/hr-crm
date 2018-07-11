import React, {Component} from "react";
import { withStyles } from '@material-ui/core/styles';
import Button from "@material-ui/core/Button";
import PropTypes from 'prop-types';

const styles = theme => ({
    button: {
        margin: theme.spacing.unit
    }
});

class ButtonSubmit extends Component {

    render() {
        const { classes } = this.props;

        return (
            <Button variant="contained" onClick={this.props.onClick} className={classes.button} >
                {this.props.children}
            </Button>
        );
    }
}

ButtonSubmit.propTypes = {
    classes: PropTypes.object.isRequired
};

export default withStyles(styles)(ButtonSubmit);