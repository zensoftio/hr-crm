import React from 'react';
import SelectList from '../../ui/SelectList';
import MultiSelection from '../../ui/MultiSelection';
import PlusMinus from '../../ui/PlusMinus';
import { DEPARTMENTS_URL, 
		 REQUESTS_URL, 
		 POSITIONS_URL,
		 REQUIREMENTS_URL } from '../../../utils/urls';
import { FetchDataAPI } from '../../../services/FetchDataAPI';
import { PostDataAPI } from '../../../services/PostDataAPI';
import { TextField } from '@material-ui/core';

class CreatePositionContainer extends React.Component {
	constructor(props){
		super(props)
		this.state = {
				department: [],
				position: [],
				departmentVal: [],
				positionVal: [],
				count: 0,
				requirements: [],
				reqVal: []
		};
	}
	
	componentDidMount() {

		FetchDataAPI(DEPARTMENTS_URL)
			.then(json => json.results.map(result => (
				{
					id: result.id,//id will be used when we make POST request
					name: result.name
				}
			)))
			.then(department => this.setState({
				department
			}));

	}
	
	handleSubmit = (e) => {
		e.preventDefault();	
		
		const newPost = {
			"position": this.state.positionVal,
			"created_by": 1,
			"requirements": this.state.reqVal,
			"count": this.state.count
		}
		console.log(this.state)
		//POST request
		PostDataAPI(REQUESTS_URL, newPost);
	
	}

	handleChangeDepartment = (e) => {
		this.setState({departmentVal:parseInt(e.target.value, 10)},
		() => {
			FetchDataAPI(POSITIONS_URL + `?department=${this.state.departmentVal}`)
				.then(json => json.results.map(result => (
					{
						id: result.id,
						name: result.name
					}
				)))
				.then(position => this.setState({
					position
				}))
			
		}
	);
	
	}

	handlePosition = (e) => {
		this.setState({positionVal: parseInt(e.target.value, 10)},
			() => {
				FetchDataAPI(REQUIREMENTS_URL + `?department=${this.state.positionVal}`)
					.then(json => json.results.map(result => (
						{
							id: result.id,
							name: result.name
						}
					)))			
					.then(requirements => this.setState({
						requirements
					}))				
			}	
		);
	}

	//to get a state from child component we pass 
	//function with an argument,which is gonna be set
	//to parent state
	getCountFromChild = (dataFromChild) => {
		this.setState({
			count: dataFromChild
		});
	}

	getMultiSelected = (multiItemsFromChild) => {
		this.setState({
			reqVal: multiItemsFromChild
		})
	}


    render() {
		
      return (
				<form onSubmit={this.handleSubmit}>
					<div>
						<div className="div_items">
							<label>
								<span className="labels">ОТДЕЛ:</span>							
								<SelectList optionValue={this.state.department} onChange={this.handleChangeDepartment}/>							
							</label>						
						</div>

						<div className="div_items">
							<label>
								<span className="labels">КОЛИЧЕСТВО:</span>
								<PlusMinus getCountFromChild={this.getCountFromChild}countStarts={this.state.count}/>							
							</label>
						</div>

						<div className="div_items">
							<label>
								<span className="labels">НАЗВАНИЕ:</span>	
								<SelectList optionValue={this.state.position} onChange={this.handlePosition}/>
									
							</label>						
						</div>

						<div className="div_items">
							<label>
								<span className="labels">ТРЕБОВАНИЯ:</span>	
								<span className="labels">
									<MultiSelection optionValue={this.state.requirements}
									getMultiSelected={this.getMultiSelected}/>
								</span>	
							</label>						
						</div>

						<div className="div_items">
							<label>
								<span className="labels">НАВЫКИ:</span>	
								<span className="labels">
									<MultiSelection optionValue={this.state.requirements}
									getMultiSelected={this.getMultiSelected}/>
								</span>	
							</label>						
						</div>					

						<div className="div_items">
							<label>
								<span className="labels">ОБЩИЕ ТРЕБОВАНИЯ:</span>
								<TextField multiline placeholder="введите текст" />
							</label>
						</div>
					</div>
					<div className="div_items">
					
							<input className="sbmt_btn" type="submit" value="SUBMIT" />
					</div>
				</form>
		);
    }

}

export default CreatePositionContainer;
