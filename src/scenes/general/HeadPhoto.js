import React from 'react';
import profile from '../../components/images/profile.jpg';



	const user = sessionStorage.getItem("user");
	const photo = sessionStorage.getItem("photo");

	//set default profile photo if user 
	//doesnt have it on google account
	const profilePhoto = photo ? photo : profile;

const HeadPhoto = () => {
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
            <img className="profile_photo" src={profilePhoto} alt="profile_photo"/>
            <div>
                <span style={instyle}>{user}</span><br />
                <span style={instyle}>{hod}</span>
                <span style={instyle}>{hr}</span>
            </div>
        </div>
    );
}

export default HeadPhoto;