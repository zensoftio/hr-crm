import React from 'react';
import Header from '../general/Header';
import { Link } from 'react-router-dom';

const RequestList = () => {
	const reqList = [
		{
			name: 'JS',
			created: '13.06.2017',
			amount: 2,
			status: 'Утвержден'
		},
		{
			name: 'Python',
			created: '14.02.2017',
			amount: 1,
			status: 'Отклонен'
		},
		{
			name: 'Java',
			created: '16.12.2016',
			amount: 2,
			status: 'Утвержден'
		}
	];
	return (
		<div>
			<Header title="Список Запросов" />
			<table>
				<tbody className="table_head">
				<tr>
					<th>Название</th>
					<th>Дата создания</th>
					<th>Количество</th>
					<th>Статус</th>
					<th>Действие</th>
				</tr>
				{reqList.map((list, i) => {
					return (
						<tr key={i}>
							<td>{list.name}</td>
							<td>{list.created}</td>
							<td>{list.amount}</td>
							<td>{list.status}</td>
							<td><Link to="/edit_request">Открыть</Link></td>
						</tr>
					)
				})}
				</tbody>
			</table>
		</div>
	);
}

export default RequestList;