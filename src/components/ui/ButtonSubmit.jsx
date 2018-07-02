import React, {Component} from "react";
import Button from "@material-ui/core/Button";

const styles = theme => ({
    button: {
        margin: theme.spacing.unit
    }
});

export default class ButtonSubmit extends Component {

    render() {
        return (

            <Button variant="contained" className={styles.button} >
                {this.props.children}
            </Button>
        );
    }
}