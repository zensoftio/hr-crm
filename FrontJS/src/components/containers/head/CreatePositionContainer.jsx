import React from 'react';
import SelectList from '../../ui/SelectList';
import MultiSelection from '../../ui/MultiSelection';
import TextArea from '../../ui/TextArea';
import PlusMinus from '../../ui/PlusMinus';

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
		
		fetch('http://159.65.153.5/api/v1/departments' )
			.then(res => res.json())
			.then(json => json.results.map(result => (
				{
					id: result.id,//id will be used when we make POST request
					name: result.name
				}
			)))
			.then(department => this.setState({
				department
			}))
			.catch(err => console.log('FAILED: ',err));

	}
	
	handleSubmit = (e) => {
		e.preventDefault();	
		
		const newPost = {
			"position": this.state.positionVal,
			"created_by": 1,
			"requirements": this.state.reqVal,
			"count": this.state.count
		}

		//POST request
		fetch('http://159.65.153.5/api/v1/requests', {
			method: 'post',
			headers: {
				"Content-Type": "application/json"
			},
			body: JSON.stringify(newPost)
		})
		.then(response => response.json()) 
		.then(json => console.log(json))
		.catch(err => console.log(err))
	}

	handleChangeDepartment = (e) => {
		this.setState({departmentVal:parseInt(e.target.value)},
		() => {
			fetch(`http://159.65.153.5/api/v1/positions?department=${this.state.departmentVal}`)
			.then(res => res.json())
			.then(json => json.results.map(result => (
				{
					id: result.id,
					name: result.name
				}
			)))
			//.then(req => console.log(req))
			.then(position => this.setState({
				position
			}))
			.catch(err => console.log('FAILED: ',err));
		}
	);
	
	}

	handlePosition = (e) => {
		this.setState({positionVal: parseInt(e.target.value)},
		() => {
			fetch(`http://159.65.153.5/api/v1/requirements?department=${this.state.positionVal}`)
			.then(res => res.json())
			.then(json => json.results.map(result => (
				{
					id: result.id,
					name: result.name
				}
			)))			
			.then(requirements => this.setState({
				requirements
			}))				
			.catch(err => console.log('FAILED: ',err));
		}	
	);
	console.log(this.state.requirements);
	
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
