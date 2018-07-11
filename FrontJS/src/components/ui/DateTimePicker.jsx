import React, { Component } from 'react';
import TextField from '@material-ui/core/TextField';
import { withStyles } from '@material-ui/core/styles';
import PropTypes from 'prop-types';

const styles = () => ({
  container: {
    display: 'flex',
    flexWrap: 'wrap',
  },
});

class DateTimePickers extends Component {
  render() {
    const { classes } = this.props;

    return (
      <div>
        <form noValidate className={classes.container}>
        <TextField
          id="datetime-local"
          label={this.props.label}
          type="datetime-local"
          InputLabelProps={{
            shrink: true,
          }}
        />
      </form>
      </div>
    );
  }
}

DateTimePickers.propTypes = {
  classes: PropTypes.object.isRequired,
};


export default withStyles(styles)(DateTimePickers)