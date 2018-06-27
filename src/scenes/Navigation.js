import React from 'react';
import { Link } from 'react-router-dom';
import HeadPhoto from './HeadPhoto';

const Navigation = () => {
    return (
        <div className="navigation">
            <HeadPhoto />
            <ul className="nav-ul">
                <li><Link to="/">Home</Link></li>
                <li><Link to="/create_position">СОЗДАТЬ ПОЗИЦИЮ</Link></li>
                <li><Link to="/opened_positions">ОТКРЫТЫЕ ПОЗИЦИИ</Link></li>
                <li><Link to="/archive">АРХИВ ПОЗИЦИЙ</Link></li>
                <li><Link to="/statistics">СТАТИСТИКА</Link></li>
            </ul>
        </div>
    )
}

export default Navigation;