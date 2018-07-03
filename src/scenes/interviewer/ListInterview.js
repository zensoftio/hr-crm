import React from 'react';
import Header from '../general/Header';
import { Link } from 'react-router-dom';
import TableList from '../../components/ui/Table';

const ListInterview = () => {
	const linkEvaluate = <Link to="/candidate_grade">открыть</Link>;

	const data = [
		[
			'Jason Born', '12.12.2012', 'first', 'Прошло', linkEvaluate
		],
		[
			'James Bond', '10.02.2015', 'first', 'Отклонен', linkEvaluate
		],
		[
			'Johnny Cage', '02.11.2016', 'first', 'Утвержден', linkEvaluate
		]
	],
	header = [
		'#', 'ФИО', 'Дата', 'Место', 'Статус', 'Оценка'
	];	
	return (
		<div>
			<Header title="Список Интервью" />
			
			<TableList header={header} data={data}/>
		</div>
	);
}

export default ListInterview;