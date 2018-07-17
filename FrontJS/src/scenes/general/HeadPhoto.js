import React from 'react';
import profile from '../../components/images/profile.jpg';

//set default profile photo if user 
//doesnt have it on google account
const HR = 'Human Resourses';

class HeadPhoto extends React.Component {
    constructor (props) {
        super(props);
        this.state = {
            photo: profile,
            name: 'User',
            role: HR
        }
    }

    componentDidMount () {
        this.setState({
            photo: JSON.parse(localStorage.getItem("photo")),
            name: JSON.parse(localStorage.getItem("username"))
        })
    }

    render () {
        return (
            <div className="headphoto">
                <img className="profile_photo" src={this.state.photo} alt="profile_photo"/>
                <div>
                    <span style={style}>{this.state.name}</span><br />
                </div>
            </div>
        )
    }

}

let style = {
    display: 'inline-block',
    margin: 4,
            color: '#d7f8f7',
            cursor: 'pointer'
}

export default HeadPhoto;