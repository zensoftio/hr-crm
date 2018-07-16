import './index.css';
import React, { Component } from 'react';
import {GoogleAPI, GoogleLogin, GoogleLogout} from 'react-google-oauth'
/* User Roles */
import User from './Roles';
import { PostDataAPI } from './services/PostDataAPI';
import { CLIENT_ID } from './utils/urls';


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
        const authData = googleUser.getAuthResponse();
        
        
        let tempStorage =  window.sessionStorage;
            tempStorage.setItem("username", userData.ig)
            tempStorage.setItem("photo", userData.Paa)
        this.setState({
            session: userData
        })	
        const bodyAuth = {
            grant_type: "convert_token",
            client_id: CLIENT_ID,
            client_secret:"sM1sLOV08DcAXVhGv9jQcBpQ",
            backend:"google-oauth2",
            token: authData.access_token
        }

        PostDataAPI('http://192.168.89.129:8000/api/v1/auth/convert-role_token', bodyAuth)
    }

    render() {
        if(this.state.session) {
            return <User userRole="hr" />
        }
        return(
            <div>                
                <GoogleAPI clientId={CLIENT_ID}
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
