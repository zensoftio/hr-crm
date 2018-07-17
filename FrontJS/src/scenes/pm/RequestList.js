import React from 'react';
import Header from '../general/Header';
import { Link } from 'react-router-dom';
import TableList from '../../components/ui/Table';
import { FetchDataAPI } from '../../services/FetchDataAPI';
import DateConvert from '../../utils/DateConvert';
import { REQUESTS_URL } from '../../utils/urls';

class RequestList extends React.Component {
	constructor(props){
		super(props);
		this.state = {
			data: []
		}

	}

	componentDidMount() {
		
		FetchDataAPI(REQUESTS_URL + '?size=1000' )
		.then(response => response.results.map(res => 	
			[
				<Link to={`/edit_request/${res.id}`}>{res.position.name}</Link>,
					DateConvert(res.created),
					res.count,
					res.status
			]	
		))
		.then(data => this.setState({ data }))
		 .then(resp => console.log(resp))		
	}

	render() {

		const header = [
			'#', 'Название', 'Дата создания', 'Количество', 'Статус'
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