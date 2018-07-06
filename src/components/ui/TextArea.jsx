import React, { Component } from "react";
import TextField from "@material-ui/core/TextField";

const styles = theme => ({
  textArea: {
    marginLeft: theme.spacing.unit,
    marginRight: theme.spacing.unit
  },
});

export default class TextArea extends Component {
  state = {
    multiline: "Controlled"
  };

  handleChange = name => event => {
    this.setState({
      [name]: event.target.value
    });
  };

  render() {
    return (
      <div className={styles.cont}>
        <TextField
          id="textArea-flexible"
          placeholder="введите текст"
          multiline
          rows="5"
          onChange={this.handleChange("multiline")}
          className={styles.textArea}
          margin="normal"
        />
      </div>
    );
  }
}
