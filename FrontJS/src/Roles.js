import React, {Component} from "react";
/* User roles */
import Admin from "./scenes/general/Admin";
import Head from "./scenes/head/HeadScene";
import HR from "./scenes/hr/HRScene";
import Interviewer from "./scenes/interviewer/Interviewer";
import PM from './scenes/pm/PM';
import Error from "./scenes/general/Error";

export default class UserRole extends Component {
    render() {
        const userRole = this.props.userRole;

        if (userRole === "admin") {
            return <Admin/>;
        } else if (userRole === "head") {
            return <Head/>;
        } else if (userRole === "hr") {
            return <HR/>;
        } else if (userRole === "interviewer") {
            return <Interviewer/>;
        } else if (userRole === "pm") {
            return <PM/>;
        } else {
            return <Error/>;
        }
    }
}