import React from 'react';

export default class PlusMinus extends React.Component {
	constructor(props) {
    super(props);
    this.state = {
			count: 0		
		};
		
	}

	increment = () => {
		this.setState( prev => ({
			count: prev.count + 1
		}))
	}

	decrement = () => {
		this.setState(prev => ({
			count: (prev.count > 0) ? prev.count - 1 : 0
		}))
	}	

	render() {

		let btnstyle = {
			margin: '0 20px'
		}

		return (
			<span>
				<button 
					onClick={this.decrement} 
					style={btnstyle}>-</button>
				<span>{this.state.count}</span>
				<button 
					onClick={this.increment} 
					style={btnstyle}>+</button>				
			</span>
		);
	}

}