import React from 'react';
import { Link } from 'react-router-dom';
import Header from '../general/Header';
import DateConvert from '../../utils/DateConvert';

class OpenedPositions extends React.Component {
	constructor(props) {
    super(props);
    this.state = {
			positionList: []		
		};
	}

	componentDidMount() {
		this.fetchData();
	}
	fetchData(){
		fetch('http://159.65.153.5/api/v1/requests')
			.then(res => res.json())			
			.then(json => json.results.map(result => (
				{
					position: result.position.name,
					created: DateConvert(result.created)
				}
			)))
			.then(positionList => this.setState({
				positionList
			}))
			.catch(err => console.log('FAILED : ',err))
			
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
									<button onClick={() => this.handleConfirm(item)}>удалить</button>
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