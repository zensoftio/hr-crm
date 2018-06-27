import React from 'react';
import { Link } from 'react-router-dom';
import HeadPhoto from './HeadPhoto';

const Navigation = () => {
    return (
        <div className="navigation">
            <HeadPhoto />
            <ul className="nav-ul">
                <li><Link to="/">Home</Link></li>
                <li><Link to="/create_position">Создать Позицию</Link></li>
                <li><Link to="/opened_positions">Открытые Позиции</Link></li>
                <li><Link to="/archive">Архив Позиций</Link></li>
                <li><Link to="/statistics">Статистика</Link></li>
                
            </ul>
        </div>
    )
}

export default Navigation;