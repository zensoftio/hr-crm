import './index.css';
import React, {Component} from 'react';
/* User Roles */
import User from './Roles';

class App extends Component {  
  render() {
    return (
      <div>
        
            <User userRole="hr" />{/*Put the department in which you develop: head, hr, pm, interviewer*/}
        
      </div>
    );
  }

};

export default App;