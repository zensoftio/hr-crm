import React from 'react';
import { withStyles } from '@material-ui/core/styles';
import { MenuItem, Chip, Select, FormControl } from '@material-ui/core';

const styles = (theme) => ({
  formControl: {
    margin: theme.spacing.unit,
    minWidth: 120,
  },
  chips: {
    display: 'flex',
    flexWrap: 'wrap',
  },
  chip: {
    margin: theme.spacing.unit / 4,
  },
})

const ITEM_HEIGHT = 48;
const ITEM_PADDING_TOP = 8;
const MenuProps = {
  PaperProps: {
    style: {
      maxHeight: ITEM_HEIGHT * 4.5 + ITEM_PADDING_TOP,
    },
  },
};

class MultipleSelect extends React.Component {
  state = {
    value: [],
  };

  handleChange = event => {
    this.setState({ value: event.target.value }, console.log(event.target));
  };

  render() {
    const { classes, optionValue, name } = this.props;

    return (
      <FormControl className={classes.formControl}>
          <Select
            multiple
            className={classes.chipBox}
            value={this.state.value}
            onChange={this.handleChange}
            renderValue={selected => (
              <div className={classes.chips}>
                {selected.map(value => <Chip key={value} label={value} className={classes.chip} />)}
              </div>
            )}
            autoWidth
            MenuProps={MenuProps}
            displayEmpty>
            { 
              optionValue.map(item => (
                <MenuItem
                  key={item}
                  value={item}
                  name={name}>
                  {item}
                </MenuItem>
              )) 
            }
          </Select>
      </FormControl>
    );
  }
}

export default withStyles(styles) (MultipleSelect);
