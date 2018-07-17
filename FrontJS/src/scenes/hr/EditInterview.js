import React, { Component } from 'react';
import { Paper } from '@material-ui/core';
import { withStyles } from '@material-ui/core/styles';
import EditInterviewContainer from "../../components/containers/hr/EditInterviewContainer";
import Header from "../general/Header";

const styles = () => ({
    paperBox: {
        margin: '1.5em 1em',
        padding: '1em 1.5em'
    }
})

class EditInterview extends Component {
    render() {
        const { classes } = this.props;

        return (
            <div>
                <Header title="Изменить интервью"/>
                <Paper className={classes.paperBox}>
                    <EditInterviewContainer interviewId={this.props.match.params.id} />
                </Paper>
            </div>

        );
    }
}

export default withStyles(styles) (EditInterview);