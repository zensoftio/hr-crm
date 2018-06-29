import React from 'react';
import profile from '../../components/images/profile.jpg';

const HeadPhoto = () => {
    let name = 'John Doe';
		let hod = 'HoD';
		let hr = 'HR';
    let instyle = {
        display: 'inline-block',
        margin: 4,
				color: '#d7f8f7',
				cursor: 'pointer'
    }

    return (
        <div className="headphoto">
            <img className="profile_photo" src={profile} alt="profile_photo"/>
            <div>
                <span style={instyle}>{name}</span><br />
                <span style={instyle}>{hod}</span>
								<span style={instyle}>{hr}</span>
            </div>
        </div>
    );
}

export default HeadPhoto;