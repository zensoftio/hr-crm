import React from 'react';
import Header from '../general/Header';

class OpenedPositions extends React.Component {
	constructor(props) {
    super(props);
    this.state = {
			positionList: [
				{
					position: 'Python',
					created: '12.12.2012'
				},
				{
					position: 'JS',
					created: '12.2.2013'
				},
				{
					position: 'iOS',
					created: '12.2.2013'
				}
			]		
		};
	}

	handleDeleteList = (listToDelete) => {
		this.setState(prev => ({
			positionList: prev.positionList.filter(list => list !== listToDelete)
		}))
	}
	handleConfirm = (listToDelete) => {
		const popup = window.confirm("Do You Want to Delete?");
		if(popup === true) {
			this.setState(prev => ({
				positionList: prev.positionList.filter(list => list !== listToDelete)
			}))
		}
	}
	//rendering
	render() {
		return(
			<div>
				<Header title="Открытые Позиции"/>

				<table>
					<tbody className="table_head">
					<tr>
						<th>Название</th>
						<th>Дата создания</th>
						<th>Действия</th>
					</tr>
					{this.state.positionList.map((item, i) => {
						return (
							<tr key={i}>
								<td>{item.position}</td>
								<td>{item.created}</td>
								<td>
									<button onClick={() =>this.handleConfirm(item)}>удалить</button>
								</td>
							</tr>
						)
					})}
					</tbody>
				</table>
			</div>
		);
	}

}

{/*
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
		{
			position: 'HTML-Верстальщик',
			created: '10.5.2013'
		},
		{
			position: 'Full-Stack Developer',
			created: '11.12.2013'
		},
		{
			position: 'IOS',
			created: '14.2.2013'
		}
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
*/}
export default OpenedPositions;