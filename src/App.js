import './index.css';
import React, { Component } from 'react';
import { BrowserRouter as Router } from "react-router-dom";
/* User Roles */
import User from './Roles';

class App extends Component {  
  render() {
    return (
      <div>
        <Router>
            <User userRole="head" />{/*Put the department in which you develop: head, hr, pm, interviewer*/}
        </Router>
      </div>
    );
  }
};

export default App;