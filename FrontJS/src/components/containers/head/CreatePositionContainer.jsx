import React from 'react';
import SelectList from '../../ui/SelectList';
import MultiSelection from '../../ui/MultiSelection';
import TextArea from '../../ui/TextArea';
import PlusMinus from '../../ui/PlusMinus';
import { DEPARTMENTS_URL, 
		 REQUESTS_URL, 
		 POSITIONS_URL,
		 REQUIREMENTS_URL } from '../../../utils/urls';
import { FetchDataAPI } from '../../../services/FetchDataAPI';
import { PostDataAPI } from '../../../services/PostDataAPI';

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
		let instyle = {
			display: 'inline-block',
			marginRight: 20
		};
		let divstyle = {
			margin: 20
		}	
		
      return (
				<form onSubmit={this.handleSubmit}>
					<div>
						<div style={divstyle}>
							<label>
								<span style={instyle}>ОТДЕЛ:</span>							
								<SelectList optionValue={this.state.department} onChange={this.handleChangeDepartment}/>							
							</label>						
						</div>

						<div style={divstyle}>
							<label>
								<span style={instyle}>КОЛИЧЕСТВО:</span>
								<PlusMinus getCountFromChild={this.getCountFromChild}/>							
							</label>
						</div>

						<div style={divstyle}>
							<label>
								<span style={instyle}>НАЗВАНИЕ:</span>	
								<SelectList optionValue={this.state.position} onChange={this.handlePosition}/>
									
							</label>						
						</div>

						<div style={divstyle}>
							<label>
								<span style={instyle}>ТРЕБОВАНИЯ:</span>	
								<span style={instyle}>
									<MultiSelection optionValue={this.state.requirements}
									getMultiSelected={this.getMultiSelected}/>
								</span>	
							</label>						
						</div>

						<div style={divstyle}>
							<label>
								<span style={instyle}>НАВЫКИ:</span>	
								<span style={instyle}>
									<MultiSelection optionValue={this.state.requirements}
									getMultiSelected={this.getMultiSelected}/>
								</span>	
							</label>						
						</div>					

						<div style={divstyle}>
							<label>
								<span style={instyle}>ОБЩИЕ ТРЕБОВАНИЯ:</span>
								<TextArea />
							</label>
						</div>
						
						<div style={divstyle}>
						</div>	
					</div>
							<input type="submit" value="Submit" />
				</form>
		);
    }

}

export default CreatePositionContainer;
