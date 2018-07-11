import React from 'react';
import Select from '../../ui/Select';
import MultipleSelect from '../../ui/MultipleSelect';
import TextArea from '../../ui/TextArea';
import ButtonSubmit from '../../ui/ButtonSubmit';
import PlusMinus from '../../ui/PlusMinus';

class CreatePositionContainer extends React.Component {

    render() {
		const department = [
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
			
			
      return (
			<div>
				<div style={divstyle}>
					<label>
						<span style={instyle}>ОТДЕЛ:</span>							
						<Select optionValue={department}/>							
					</label>						
				</div>

				<div style={divstyle}>
					<label>
						<span style={instyle}>КОЛИЧЕСТВО:</span>
						<PlusMinus />							
					</label>
				</div>

				<div style={divstyle}>
					<label>
						<span style={instyle}>НАЗВАНИЕ:</span>	
						<Select optionValue={developer}/>
							
					</label>						
				</div>

				<div style={divstyle}>
					<label>
						<span style={instyle}>ТРЕБОВАНИЯ:</span>	
						<span style={instyle}>
							<MultipleSelect optionValue={requirements}/>
						</span>	
					</label>						
				</div>

				<div style={divstyle}>
					<label>
						<span style={instyle}>НАВЫКИ:</span>	
						<span style={instyle}>
							<MultipleSelect optionValue={requirements}/>
						</span>	
					</label>						
				</div>

				<div style={divstyle}>
					<label>
						<span style={instyle}>УРОВЕНЬ:</span>							
						<Select optionValue={rank}/>							
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