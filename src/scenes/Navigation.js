import React from 'react';
import { Link } from 'react-router-dom';
import HeadPhoto from './HeadPhoto';

const Navigation = () => {
    return (
        <div className="navigation">
            <HeadPhoto />
            <ul className="nav-ul">
                <li><Link to="/">Home</Link></li>
                <li><Link to="/create_position">Create Position</Link></li>
                <li><Link to="/opened_positions">Opened Positions</Link></li>
                <li><Link to="/archive">Archive Position</Link></li>
                <li><Link to="/statistics">Statistics</Link></li>
                
            </ul>
        </div>
    )
}

export default Navigation;