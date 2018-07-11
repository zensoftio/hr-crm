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
        name={this.props.name}
        label={this.props.label} 
        />
      </div>
    );
  }
}

export default CheckboxControl;