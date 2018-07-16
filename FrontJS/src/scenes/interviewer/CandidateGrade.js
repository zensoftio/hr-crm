import React from 'react';
import Header from '../general/Header';
import EvaluateRange from '../../components/ui/EvaluateRange';
import Button from '@material-ui/core/Button';

const CandidateGrade = () => {
	const evaluate = <EvaluateRange />
	const skills = [
		'Passion for Coding', 'Technical Communication', 'Time Management', 'Teamwork', 'Communication skills', 'Developer Personality', 'Punctuality', 'competency development ', 'TASK MANAGEMENT', 'QUICK LEARNING ABILITY' 
	];
	const instyle = {
		margin: 10
	}
	
	function myFunc(arr) {
		return arr.map((item, i) => {
			return (
				<div style={instyle} key={i}>
					<span>{item.toUpperCase()}: </span>
					<span>{evaluate}</span>
				</div>
			)}					
		);
	}
	
	return (
		<div>
			<Header title="Оценка Кандидата" />
			<div>						
				{myFunc(skills)}
				<Button variant="contained">SUBMIT</Button>
			</div>
		</div>
	);
}

export default CandidateGrade;