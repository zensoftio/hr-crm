import React from 'react';
import { MenuItem } from '@material-ui/core';

const RenderSelectItem = (props) => {
    return props.map((item, index) => (
        <MenuItem key={index} value={item}>{item}</MenuItem>
    ))
}

export default RenderSelectItem;