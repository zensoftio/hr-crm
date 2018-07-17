import React from 'react';
import PlusMinus from '../../ui/PlusMinus';
import { REQUESTS_URL } from '../../../utils/urls';
import { FetchDataAPI } from '../../../services/FetchDataAPI';
import { PutDataAPI } from '../../../services/PutDataAPI';
import MultipleSelect from '../../ui/MultipleSelect';

class EditPositionContainer extends React.Component {
	constructor(props) {
		super(props)
		this.state = {
			department :'',
			count : 0,
			position : '',
			requirements : [],
			url: REQUESTS_URL + '/' + this.props.position_id
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

	handleSubmit = (e) => {
		e.preventDefault();
		const {count} = this.state;
		PutDataAPI(this.state.url,{"count": count})	;
	}
	
    render() {
				
			const { department, position, count, requirements } = this.state;
		
      return (
				<form onSubmit={this.handleSubmit}>
					<div>

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
									<MultipleSelect optionValue={requirements}
									/>
								</span>	
							</label>						
						</div>

						<div className="div_items">					
							<input className="sbmt_btn" type="submit" value="SUBMIT" />
						</div>
					</div>
				</form>
		);
    }

	
}

export default EditPositionContainer;