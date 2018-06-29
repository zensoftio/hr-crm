import React from 'react';
import { Link } from 'react-router-dom';
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

	handleConfirm = (listToDelete) => {
		const popup = window.confirm("Do You Want to Delete?");
		if(popup === true) {
			this.setState(prev => ({
				positionList: prev.positionList.filter(list => list !== listToDelete)
			}))
		}
	}
	
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
								<td><Link to="/edit_positions">{item.position}</Link></td>
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

export default OpenedPositions;