import React from 'react';
import Header from '../general/Header';
import EvaluateRange from '../../components/ui/EvaluateRange';

const CandidateGrade = () => {
	const evaluate = <EvaluateRange />
	const skills = [
		'Passion for Coding', 'Technical Communication', 'Time Management', 'Teamwork', 'Communication skills', 'Passion for Coding', 'Technical Communication', 'Time Management', 'Teamwork', 'Communication skills' 
	];
	const instyle = {
		margin: 10
	}
	
	function myFunc(arr) {
		return arr.map((item, i) => {
			return (
				<div style={instyle} key={i}>
					<span>{item}: </span>
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
			</div>
		</div>
	);
}

export default CandidateGrade;