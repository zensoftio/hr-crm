import './index.css';
import React, { Component } from 'react';
// import {GoogleAPI, GoogleLogin, GoogleLogout} from 'react-google-oauth'
/* User Roles */
import User from './Roles';

// const FailureHandle = () => {
//     return <h1>Something went wrong</h1>
// }

const url = "https://accounts.google.com/o/oauth2/auth?client_id=161387635546-54csodkv5tms1nnflj33o8c9rhq3srhv&redirect_uri=https://hr-crm-front.herokuapp.com&response_type=code&scope=email";
export default class App extends Component { 
    constructor(props) {
        super(props)
        this.state = {
            session: window.sessionStorage.user
        }
    }

    signIn = (googleUser) => {
        const userData = googleUser.getAuthResponse();
        let tempStorage =  window.sessionStorage;
        tempStorage.setItem("user", userData.ig);
        this.setState({
            session: userData
        })
        console.log(userData);
    }

    render() {
        if(this.state.session) {
            return <User userRole="hr" />
        }
        return(
            <div>
                <a href={url}>Sign in with Google</a>
                
                {/* <GoogleAPI clientId="485499920078-nm7ajq0j1spkul2jlnv9j1g579fbiqjo.apps.googleusercontent.com"
                    onInitFailure={FailureHandle} >
                    <div>
                        <div>
                            <GoogleLogin
                             onLoginSuccess={this.signIn}
                             onLoginFailure={FailureHandle}
                            />
                         </div>
                        <div>
                            <GoogleLogout 
                            onLogoutSuccess={this.signIn}
                            />
                        </div>
                    </div>
                </GoogleAPI> */}
            </div>
        )
    }
}
