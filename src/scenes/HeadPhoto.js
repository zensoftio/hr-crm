import React from 'react';
import profile from '../components/images/profile.jpg';

const HeadPhoto = () => {
    let name = 'John Doe';
    let status = 'HoD';
    let instyle = {
        display: 'inline-block',
        margin: 4,
        color: '#d7f8f7'
    }

    return (
        <div className="headphoto">
            <img className="profile_photo" src={profile} alt="profile_photo"/>
            <div>
                <span style={instyle}>{name}</span>
                <span style={instyle}>{status}</span>
            </div>
        </div>
    );
}

export default HeadPhoto;