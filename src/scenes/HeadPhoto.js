import React from 'react';
import profile from '../components/images/profile.jpg';

const HeadPhoto = () => {
    let name = 'Name';
    return (
        <div className="headphoto">
            <img className="profile_photo" src={profile} alt="profile_photo"/>
            <p>{name}</p>
        </div>
    );
}

export default HeadPhoto;