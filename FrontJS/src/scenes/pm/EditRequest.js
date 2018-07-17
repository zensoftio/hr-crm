import React from 'react';
import Header from '../general/Header';
import MultipleSelect from '../../components/ui/MultipleSelect';
import PlusMinus from '../../components/ui/PlusMinus';
import Button from '@material-ui/core/Button'
import { TextField } from '@material-ui/core';
import { REQUESTS_URL } from '../../utils/urls';
import { FetchDataAPI } from '../../services/FetchDataAPI';
import { PutDataAPI } from '../../services/PutDataAPI';

class EditRequest extends React.Component {

	constructor(props){
		super(props)
		this.state = {
			department :'',
			count : 0,
			position : '',
			requirements : [],
			url: REQUESTS_URL + '/' + this.props.match.params.id
		}
	}

	componentDidMount() {
		FetchDataAPI( this.state.url )
			.then(response => this.setState(
				{
					department : response.position.department.name,
					count : response.count,
					position : response.position.name,
					requirements : response.requirements.map(res => res.name)
				}
			))			
	}

	getCountFromChild = (dataFromChild) => {
		this.setState({
			count: dataFromChild
		});
	}

	handleApprove = () => {
		const { url, count} = this.state;
		PutDataAPI( url, {
			"count": count,
			"status": "APPROVED"
		});
	}

	handleDecline = () => {
		const { url, count} = this.state;
		PutDataAPI( url, {
			"status": "DECLINED",
			"count": count,
		});
	}

	render() {			
			
			const { department, position, count, requirements } = this.state;

			return(
				<div>
					<Header title="Запросы"/>
						<div className="div_items">
							<label>
								<span className="labels">ОТДЕЛ:</span>							
								<select value={department}>
									<option>{department}</option>
								</select>								
							</label>						
						</div>
			
						<div className="div_items">
							<label>
								<span className="labels">КОЛИЧЕСТВО:</span>
								<PlusMinus getCountFromChild={this.getCountFromChild}  countStarts={count}/>						
							</label>
						</div>
			
						<div className="div_items">
							<label>
								<span className="labels">НАЗВАНИЕ:</span>	
								<select value={position}>
									<option>{position}</option>
								</select>								
							</label>						
						</div>
			
						<div className="div_items">
							<label>
								<span className="labels">ТРЕБОВАНИЯ:</span>	
								<span className="labels">
									<MultipleSelect optionValue={requirements}/>
								</span>	
							</label>						
						</div>
					
						<div className="div_items">
							<label>
								<span className="labels">ОБЩИЕ ТРЕБОВАНИЯ:</span>
								<TextField multiline placeholder="введите текст" />
							</label>
						</div>
			
						<div className="div_items">
							<span className="labels">
								<Button 
									onClick={this.handleApprove}
									variant="contained" 
									color="primary">Утвердить
								</Button>
							</span>
							<span className="labels">
								<Button 
									onClick={this.handleDecline}
									variant="contained" color="secondary">Отклонить
								</Button>
							</span>
						</div>
					
				</div>
			);

	}
}

export default EditRequest;