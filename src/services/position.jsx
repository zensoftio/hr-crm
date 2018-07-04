
import React from 'react';

class PositionService extends React.Component {
		constructor(props) {
			super(props);

		}
    componentDidMount() {
			this.fetchData();
		}
		fetchData(){
			fetch('https://private-anon-fc0d4b79ec-zensofthr.apiary-mock.com/api/v1/requests?status=&department=')
				.then(res => res.json())
				.then(json => console.log(json))
				.catch(err => console.log(err))
				
		}

		render() {
			return (
				<div></div>
			);
		}
}


export default PositionService ;