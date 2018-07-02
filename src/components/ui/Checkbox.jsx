import React, { Component } from "react";
import FormControlLabel from '@material-ui/core/FormControlLabel';
import Checkbox from '@material-ui/core/Checkbox';

class CheckboxControl extends Component {
  render() {
    return (
      <div>
        <FormControlLabel 
        control={
            <Checkbox />
        } 
        label={this.props.label} 
        />
      </div>
    );
  }
}

export default CheckboxControl;