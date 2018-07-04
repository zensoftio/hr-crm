import React from 'react';
import Header from '../general/Header';
import SelectList from '../../components/ui/SelectList';
import MultipleSelect from '../../components/ui/MultipleSelect';
import PlusMinus from '../../components/ui/PlusMinus';
import TextArea from '../../components/ui/TextArea';

const EditRequest = () => {

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
	requirements = [
		'one',
		'two',
		'three',
		'five',
		'four'
	],
	additional_requirements = [
		'lorem',
		'ipsum',
		'dolores',
		'katanta'
	]

	const instyle = {
		display: 'inline-block',
		marginRight: 20
	};
	const divstyle = {
		margin: 20
	}
	const btnstyle = {
		padding: '10px 15px',
		margin: '0px 20px'
	}

	return(
		<div>
			<Header title="Запросы"/>

			<div style={divstyle}>
				<label>
					<span style={instyle}>ОТДЕЛ:</span>							
					<SelectList vals={department}/>							
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
					<span style={instyle}>ДОПОЛНИТЕЛЬНЫЕ ТРЕБОВАНИЯ:</span>	
					<span style={instyle}>
						<MultipleSelect names={additional_requirements}/>
					</span>	
				</label>						
			</div>

			<div style={divstyle}>
				<label>
					<span style={instyle}>ОБЩИЕ ТРЕБОВАНИЯ:</span>
					<TextArea />
				</label>
			</div>

			<div style={divstyle}>
				<button style={btnstyle}>Утвердить</button>
				<button style={btnstyle}>Отклонить</button>
			</div>
			
		</div>
	);
}

export default EditRequest;