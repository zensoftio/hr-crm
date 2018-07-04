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
        this.setState({
            session: userData
        })
    }

    render() {
        if(this.state.session) {
            return <User userRole="pm" />
        }
        return(
            <div>
                <GoogleAPI clientId="485499920078-nm7ajq0j1spkul2jlnv9j1g579fbiqjo.apps.googleusercontent.com"
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
