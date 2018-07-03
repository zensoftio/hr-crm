import React from 'react';
import Header from '../general/Header';
import TableList from '../../components/ui/Table';
const Archive = () => {
	let today = new Date().getDate() + '.' + (new Date().getMonth() + 1) + '.' + new Date().getFullYear();
	
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
			
			<TableList header={header} data={data} />
    </div>
  )
}

export default Archive;