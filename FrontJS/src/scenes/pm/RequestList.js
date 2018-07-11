import React from 'react';
import Header from '../general/Header';
import { Link } from 'react-router-dom';
import TableList from '../../components/ui/Table';
import { FetchDataAPI } from '../../services/FetchDataAPI';
import DateConvert from '../../utils/DateConvert';

class RequestList extends React.Component {
	constructor(props){
		super(props);
		this.state = {
			data: []
		}

	}

	componentDidMount() {
		 const openLink = <Link to="/edit_request">Открыть</Link>;
		const fetched = FetchDataAPI(`http://159.65.153.5/api/v1/requests`);
		fetched
		.then(response => response.results.map(res => 	
			[
					res.position.name,
					DateConvert(res.created),
					res.count,
					res.status,
					openLink
			]	
		))
		.then(data => this.setState({ data }))		
	}

	render() {

		const header = [
			'#', 'Название', 'Дата создания', 'Количество', 'Статус', 'Действие'
		];	
		
		return (
			<div>
				<Header title="Список Запросов" />
				
				<TableList header={header} data={this.state.data}/>
			</div>
		);
	}
}

export default RequestList;