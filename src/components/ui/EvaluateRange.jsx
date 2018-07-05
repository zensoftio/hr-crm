import React from 'react';

const EvaluateRange = () => {
	const instyle = {
		padding: '3px 10px',
		fontSize: '18px'
	}
	return (
		<span>
			<input type="number" max="10" min="1" style={instyle}/>
		</span>
	);
}

export default EvaluateRange;