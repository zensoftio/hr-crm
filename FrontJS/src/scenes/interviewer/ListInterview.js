import React from 'react';
import Header from '../general/Header';
import { Link } from 'react-router-dom';
import TableList from '../../components/ui/Table';
import { FetchDataAPI } from '../../services/FetchDataAPI';
import { INTERVIEWS_URL } from '../../utils/urls';
import DateConvert from '../../utils/DateConvert';

class ListInterview extends React.Component{
	constructor(props){
		super(props)
		this.state = {
			data: []
		}
	}

	componentDidMount() {
		const linkEvaluate = <Link to="/candidate_grade">открыть</Link>;

		FetchDataAPI( INTERVIEWS_URL )
			.then(resp => resp.results.map(result => 
				[
					result.candidate.first_name + ' ' + result.candidate.last_name,
					DateConvert(result.date),
					result.status,
					linkEvaluate
				]
			))
			.then(data => this.setState({
				data
			}))
			
	}
	render(){
	
		const header = [
			'#', 'ФИО', 'Дата', 'Статус', 'Оценка'
		];	


		return (
			<div>
				<Header title="Список Интервью" />
				
				<TableList header={header} data={this.state.data}/>
			</div>
		);
	}
}

export default ListInterview;