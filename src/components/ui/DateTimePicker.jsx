import React, { Component } from 'react';
import TextField from '@material-ui/core/TextField';

export default class DateTimePickers extends Component {
  render() {
    return (
      <div>
        <form noValidate>
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