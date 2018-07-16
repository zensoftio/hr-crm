import React from 'react';

const SelectList = (props) => {
	const {optionValue, onChange} = props;
	let instyle = {
		display: 'inline-block'
	}
	
	return (
			<div style={instyle}>
				<select defaultValue="выбрать" 
					onChange={onChange}>
					<option disabled>выбрать</option>
					{optionValue.map((val, index) =>
						 <option key={index} value={val.id}>
						 		{val.name}
						 </option>)}	
				</select>
			</div>
	)
}
export default SelectList;