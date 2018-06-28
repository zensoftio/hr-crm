import React from 'react';
import zensoft from '../../components/images/zensoft.jpg';

const Home = () => {
    let instyle = {
        textAlign: 'center'
    }
    return (
        <div style={instyle}>
            <img src={zensoft} alt="zensoft logo"/>
        </div>
    )
}

export default Home;