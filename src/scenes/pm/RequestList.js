import React from 'react';
import Header from '../general/Header';
import { Link } from 'react-router-dom';
import TableList from '../../components/ui/Table';

const RequestList = () => {
	const openLink = <Link to="/edit_request">Открыть</Link>;

	const data = [
		[
			'Python', '12.12.2012', 2, 'Утвержден', openLink
		],
		[
			'JS', '10.02.2015', 1, 'Отклонен', openLink
		],
		[
			'iOS', '02.11.2016', 1, 'Утвержден', openLink
		]
	],
	header = [
		'#', 'Название', 'Дата создания', 'Количество', 'Статус', 'Действие'
	];	

	return (
		<div>
			<Header title="Список Запросов" />
			
			<TableList header={header} data={data}/>
		</div>
	);
}

export default RequestList;