import React from 'react';
import Header from '../general/Header';
import Select from '../../components/ui/Select';
import MultipleSelect from '../../components/ui/MultipleSelect';
import PlusMinus from '../../components/ui/PlusMinus';
import Button from '@material-ui/core/Button'
import { TextField } from '@material-ui/core';

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
	};

	return(
		<div>
			<Header title="Запросы"/>

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
					<span style={instyle}>ДОПОЛНИТЕЛЬНЫЕ ТРЕБОВАНИЯ:</span>	
					<span style={instyle}>
						<MultipleSelect optionValue={additional_requirements}/>
					</span>	
				</label>						
			</div>

			<div style={divstyle}>
				<label>
					<span style={instyle}>ОБЩИЕ ТРЕБОВАНИЯ:</span>
					<TextField multiline placeholder="введите текст" />
				</label>
			</div>

			<div style={divstyle}>
				<span style={instyle}><Button variant="contained" color="primary">Утвердить</Button></span>
				<span style={instyle}><Button variant="contained" color="secondary">Отклонить</Button></span>
			</div>
			
		</div>
	);
}

export default EditRequest;