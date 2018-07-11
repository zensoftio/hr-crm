import React from 'react';

const SelectList = (props) => {
	let vals = props.vals;
	let instyle = {
		display: 'inline-block'
	}
	console.log(props);
	
	return (
			<div style={instyle}>
				<select defaultValue={vals[0]}>
					{vals.map((val, i) => <option key={i} value={val}>{val}</option>)}	
				</select>
			</div>
	)
}
export default SelectList;