import React from 'react';
import { Link } from 'react-router-dom';
import Header from '../general/Header';
import DateConvert from '../../utils/DateConvert';
import { REQUESTS_URL } from '../../utils/urls';
import { FetchDataAPI } from '../../services/FetchDataAPI';

class OpenedPositions extends React.Component {
	constructor(props) {
    super(props);
    this.state = {
			positionList: []		
		};
	}

	componentDidMount() {
		FetchDataAPI( REQUESTS_URL + '?size=1000' )
			.then(json => json.results.map(result => (
				{
					position: result.position.name,
					created: DateConvert(result.created),
					id: result.id
				}
			)))
			.then(positionList => this.setState({
				positionList
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
								<td><Link to={`/edit_positions/${item.id}`}>{item.position}</Link></td>
								<td>{item.created}</td>
								<td>
									<button className="del_btn" onClick={() => this.handleConfirm(item)}>удалить</button>
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