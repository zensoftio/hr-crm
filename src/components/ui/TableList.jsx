import React from 'react';
//demo version
const TableList = (props) => {
	let vals = props.vals;
	
	return (
			<div>
				{vals.map((item, i) => {
					return (
						<tr key={i}>
							<td>{item.vacancy}</td>
							<td>{item.created}</td>
							<td>{item.closed}</td>
							<td>{item.amount}</td>
						</tr>
					)
				})}
			</div>
	)
}
export default TableList;