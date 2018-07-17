import './index.css';
import React, { Component } from 'react';
import {GoogleAPI, GoogleLogin} from 'react-google-oauth'
/* User Roles */
import User from './Roles';
import axios from 'axios';
import { CLIENT_ID } from './utils/urls';


const FailureHandle = () => {
    return <h1>Something went wrong</h1>
}

export default class App extends Component { 
    constructor(props) {
        super(props)
        this.state = {
            session: '',
            role: '',
        }
    }

    signIn = (googleUser) => {

        const authData = googleUser.getAuthResponse();
        const userData = googleUser.getBasicProfile();

        let tempStorage = window.sessionStorage;
            tempStorage.setItem("username", userData.ig)
            tempStorage.setItem("photo", userData.Paa)

        this.setState({
            session: tempStorage
        })	

        const authReq = {
            grant_type: "convert_token",
            client_id: CLIENT_ID,
            client_secret:"sM1sLOV08DcAXVhGv9jQcBpQ",
            backend:"google-oauth2",
            token: authData.access_token
        }

        axios.post('https://reachthestars.ml/api/v1/auth/convert-role-token',
            authReq,
            {
                headers: {
                    'access-control-allow-headers': 'role'
                }
            }).then(res => {
                this.setState({
                    role: res.headers.role
                })
            });
        
    }

    render() {

        if(this.state.session) {
            return <User userRole='head' />     
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
                    </div>
                </GoogleAPI>
            </div>
        )
    }
}
