import React from 'react';
import Header from '../general/Header';

const OpenedPositions = () => {

	const positionList = [
		{
			position: 'Python',
			created: '12.12.2012'
		},
		{
			position: 'JS',
			created: '12.2.2013'
		},
	];
    return (
        <div>
            <Header title="Открытые Позиции"/>

						<table>
							<tbody className="table_head">
							<tr>
								<th>Название</th>
								<th>Дата создания</th>
								<th>Действия</th>
							</tr>
							{positionList.map((item, i) => {
								return (
									<tr key={i}>
										<td>{item.position}</td>
										<td>{item.created}</td>
										<td>
											<button>открыть</button>
											<button>удалить</button>
										</td>
									</tr>
								)
							})}
							</tbody>
						</table>
        </div>
    )
}

export default OpenedPositions;