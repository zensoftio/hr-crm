import './index.css';
import React, { Component } from 'react';
import {GoogleAPI, GoogleLogin, GoogleLogout} from 'react-google-oauth'
/* User Roles */
import User from './Roles';


const FailureHandle = () => {
    return <h1>Something went wrong</h1>
}

export default class App extends Component { 
    constructor(props) {
        super(props)
        this.state = {
            session: window.sessionStorage.user
        }
    }

    signIn = (googleUser) => {
				const userData = googleUser.getBasicProfile();
				
        let tempStorage =  window.sessionStorage;
				tempStorage.setItem("user", userData.ig);
				tempStorage.setItem("photo", userData.Paa);
        this.setState({
            session: userData
				})
				console.log(userData);
				
				
    }

    render() {
        if(this.state.session) {
            return <User userRole="head" />
        }
        return(
            <div>
                <GoogleAPI clientId="161387635546-54csodkv5tms1nnflj33o8c9rhq3srhv.apps.googleusercontent.com"
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
                </GoogleAPI>
            </div>
        )
    }
}
