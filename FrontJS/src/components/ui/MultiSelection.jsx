import React from 'react';
import { withStyles } from '@material-ui/core/styles';
import { MenuItem, Chip, Select, FormControl } from '@material-ui/core';

const styles = (theme) => ({
	root: {
    display: 'flex',
    flexWrap: 'wrap',
  },
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
			width: 200
    },
  },
};

class MultiSelection extends React.Component {
	constructor(props){
		super(props)
		this.state = {
				value: []
		};
	}
  
  handleChange = e => {
    this.setState({ value: e.target.value },() => this.props.getMultiSelected(this.state.value));
  };

  render() {
    const { classes, optionValue } = this.props;

    return (
      <FormControl className={classes.formControl}>
          <Select
            multiple
						className={classes.chipBox}
						value={this.state.value}
						onChange={this.handleChange}
            renderValue={selected => (
              <div className={classes.chips}>
                {selected.map((value) => <Chip key={value} label={value} className={classes.chip} />)}
              </div>
            )}            
            MenuProps={MenuProps}
            displayEmpty
						autowidth="true">
            { 
              optionValue.map((item,i) => (
                <MenuItem
                  key={i}
                  value={item.id}
									name={item.id}>
                  {item.name}
                </MenuItem>
              )) 
            }
          </Select>
      </FormControl>
    );
  }
}

export default withStyles(styles) (MultiSelection);
