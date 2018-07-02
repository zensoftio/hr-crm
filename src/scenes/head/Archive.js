import React from 'react';
import Header from '../general/Header';
import TableList from '../../components/ui/Table';
const Archive = () => {
	let today = new Date().getDate() + '.' + (new Date().getMonth() + 1) + '.' + new Date().getFullYear();
	// const archive = [
	// 	{
	// 		vacancy: 'Python',
	// 		created: '12.12.2012',
	// 		closed: today,
	// 		amount: 2
	// 	},
	// 	{
	// 		vacancy: 'JS',
	// 		created: '12.2.2013',
	// 		closed: today,
	// 		amount: 1
	// 	},
	// 	{
	// 		vacancy: 'Java',
	// 		created: '12.2.2003',
	// 		closed: today,
	// 		amount: 1
	// 	}
	// ];
	const data = [
		[
			'Python', '12.12.2012', today, 2
		],
		[
			'JS', '10.02.2015', today, 1
		],
		[
			'iOS', '02.11.2016', today, 1
		]
	],
	header = [
		'#', 'Название', 'Дата создания', 'Дата закрытия', 'Количество'
	];

	
  return (
    <div>
      <Header title="Архив Позиций"/>
			{/* <table>
				<tbody className="table_head">
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
			</table> */}
			<TableList header={header} data={data} />
    </div>
  )
}

export default Archive;