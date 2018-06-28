import React from 'react';
import Header from '../general/Header';

const Archive = () => {
	let today = new Date().getDate() + '.' + (new Date().getMonth() + 1) + '.' + new Date().getFullYear();
	const archive = [
		{
			vacancy: 'Python',
			created: '12.12.2012',
			closed: today,
			amount: 2
		},
		{
			vacancy: 'JS',
			created: '12.2.2013',
			closed: today,
			amount: 1
		},
	];

	
  return (
    <div>
      <Header title="Архив Позиций"/>
			<table>
				<tbody className="archive_devs">
				<tr>
					<th>Название</th>
					<th>Дата создания</th>
					<th>Дата закрытия </th>
					<th>Количество</th>
				</tr>
				{archive.map((item, i) => {
					return (
						<tr key={i}>
							<td>{item.vacancy}</td>
							<td>{item.created}</td>
							<td>{item.closed}</td>
							<td>{item.amount}</td>
						</tr>
					)
				})}
				</tbody>
			</table>
    </div>
  )
}

export default Archive;