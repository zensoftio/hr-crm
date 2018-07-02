import './index.css';
import React, { Component } from 'react';
/* User Roles */
import User from './Roles';

class App extends Component {  
  render() {
    return (
      <div>
<<<<<<< HEAD
				<User userRole="pm" />{/*Put the department in which you develop: head, hr, pm, interviewer*/}
=======
        <Router>
            <User userRole="head" />{/*Put the department in which you develop: head, hr, pm, interviewer*/}
        </Router>
>>>>>>> c9cb7a6ddf31a7b873ed0bdbb432e4620385f04d
      </div>
    );
  }
};

export default App;