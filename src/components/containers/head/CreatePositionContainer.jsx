import React from 'react';
import SelectList from '../../ui/SelectList';
import MultipleSelect from '../../ui/MultipleSelect';
import TextArea from '../../ui/TextArea';
import ButtonSubmit from '../../ui/ButtonSubmit';

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
			],
			requirements = [
				'one',
				'two',
				'three',
				'five',
				'four'
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
						<label>
							<span style={instyle}>ТРЕБОВАНИЯ:</span>	
							<span style={instyle}>
								<MultipleSelect names={requirements}/>
							</span>	
						</label>						
					</div>

					<div style={divstyle}>
						<label>
							<span style={instyle}>НАВЫКИ:</span>	
							<span style={instyle}>
								<MultipleSelect names={requirements}/>
							</span>	
						</label>						
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
							<TextArea />
						</label>
					</div>
					<div style={divstyle}>
						<ButtonSubmit>
							SUBMIT
						</ButtonSubmit>
					</div>	
				</div>
			);
    }

}

export default CreatePositionContainer;