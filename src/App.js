import './index.css';
import React from 'react';
//import { observer } from 'mobx-react';
import { BrowserRouter, Route, Switch } from 'react-router-dom';
import Home from './scenes/general/Home';
import OpenedPositions from './scenes/headOfDep/OpenedPositions';
import Error from './scenes/headOfDep/Error';
import Navigation from './scenes/general/Navigation';
import CreatePosition from './scenes/headOfDep/CreatePosition';
import Archive from './scenes/headOfDep/Archive';
import Statistics from './scenes/general/Statistics';

class App extends React.Component {  
  render() {
    return (
      <BrowserRouter>
        <div className="container">

          <Navigation />

          <div className="content">
            <Switch>
              <Route path="/" component={Home} exact/>
              <Route path="/create_position" component={CreatePosition}/>   
              <Route path="/opened_positions" component={OpenedPositions}/>
              <Route path="/archive" component={Archive}/>
              <Route path="/statistics" component={Statistics}/>
              <Route component={Error}/>
            </Switch>
          </div>
        </div>
      </BrowserRouter>
    );
  }
};

export default App;