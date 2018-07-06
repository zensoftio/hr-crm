import './index.css';
import React from 'react';
//import { observer } from 'mobx-react';
import { BrowserRouter, Route, Switch } from 'react-router-dom';
import Home from './scenes/Home';
import OpenedPositions from './scenes/OpenedPositions';
import Error from './scenes/Error';
import Navigation from './scenes/Navigation';
import CreatePosition from './scenes/CreatePosition';
import Archive from './scenes/Archive';
import Statistics from './scenes/Statistics';


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