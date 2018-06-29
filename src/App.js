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
            <User userRole="hr" />
        </Router>
      </div>
    );
  }
};

export default App;