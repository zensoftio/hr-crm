import React from 'react';

export default class PlusMinus extends React.Component {
	constructor(props) {
    super(props);
    this.state = {
			count: 0		
		};
		
	}
//as state updates then the second argument will update the function argument of parent Component
	increment = () => {
		this.setState( prev => ({
			count: prev.count + 1
		}), () => this.props.getCountFromChild(this.state.count))
	}

	decrement = () => {
		this.setState(prev => ({
			count: (prev.count > 0) ? prev.count - 1 : 0
		}), () => this.props.getCountFromChild(this.state.count))
	}	

	render() {

		let btnstyle = {
			margin: '0 20px'
		}
		
		return (
			
			<span>
				<span className="plusminus" 
					onClick={this.decrement} 
					style={btnstyle}>-</span>

				<span>{this.state.count}</span>
				
				<span className="plusminus" 
					onClick={this.increment} 
					style={btnstyle}>+</span>				
			</span>
		);
	}

}