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
            name: 'DAVRAN',
            role: HR
        }
    }

    componentDidMount () {
        this.setState({
            photo: sessionStorage.getItem("photo"),
            name: sessionStorage.getItem("user")
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
// const HeadPhoto = () => {
// 		let hod = 'HoD';
// 		let hr = 'HR';
//     let instyle = {
//         display: 'inline-block',
//         margin: 4,
// 				color: '#d7f8f7',
// 				cursor: 'pointer'
//     }
//     return (
//         <div className="headphoto">
//             <img className="profile_photo" src={profilePhoto} alt="profile_photo"/>
//             <div>
//                 <span style={instyle}>{user}</span><br />
//                 <span style={instyle}>{hod}</span>
//                 <span style={instyle}>{hr}</span>
//             </div>
//         </div>
//     );
// }

export default HeadPhoto;