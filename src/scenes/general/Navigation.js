import React, { Component } from 'react';
import { observer } from 'mobx-react';
import { Link } from 'react-router-dom';
import HeadPhoto from './HeadPhoto';

const CreateNavItem = (props) => {
    return props.menuItems.map((item, index) => (
        <li key={index}><Link to={item.path}>{item.name}</Link></li>  
    ))
};
@observer
export default class Navigation extends Component {
    render() {
        return (
            <div className="navigation">
                <HeadPhoto />
                <ul className="nav-ul">
                    <CreateNavItem menuItems={this.props.menuItems}/>
                </ul>
            </div>
        )
    } 
}
