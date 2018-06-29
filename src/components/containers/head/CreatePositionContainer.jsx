import React from 'react';
import SelectList from '../../ui/SelectList';
import MultipleSelect from '../../ui/MultipleSelect';


class CreatePositionContainer extends React.Component {

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
			const	department = [
				'PM Department', 
				'Human Resources',
				'Head of Department'
			],
			developer = [
				'Python Dev',
				'JS Dev',
				'Java Dev'
			],
			rank = [
				'Junior',
				'Middle',
				'Senior'
			];

			let instyle = {
				display: 'inline-block',
				marginRight: 20
			};
			let divstyle = {
				margin: 20
			}
			let btnstyle = {
				margin: '0 20px'
			}
			
		console.log(this.props);
      return(
				<div>
					<div style={divstyle}>
						<label>
							<span style={instyle}>ОТДЕЛ:</span>							
							<SelectList vals={department}/>							
						</label>						
					</div>

					<div style={divstyle}>
						<label>
							<span style={instyle}>КОЛИЧЕСТВО:</span>
							<button onClick={this.decrement} style={btnstyle}>-</button>
							<span>{this.state.count}</span>
							<button onClick={this.increment} style={btnstyle}>+</button>							
						</label>
					</div>

					<div style={divstyle}>
						<label>
							<span style={instyle}>НАЗВАНИЕ:</span>							
							<SelectList vals={developer}/>							
						</label>						
					</div>

					<div style={divstyle}>
						<MultipleSelect />
					</div>

					<div style={divstyle}>
						<label>
							<span style={instyle}>УРОВЕНЬ:</span>							
							<SelectList vals={rank}/>							
						</label>						
					</div>					

					<div style={divstyle}>
						<label>
							<span style={instyle}>ОБЩИЕ ТРЕБОВАНИЯ:</span>
							<textarea defaultValue='type something'>								
							</textarea>
						</label>
					</div>

			
					
				</div>
			);
    }

}

export default CreatePositionContainer;