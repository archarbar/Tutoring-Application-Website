import React from 'react';
import './App.css';
import ButtonAppBar from "./Components/TopBar.js"
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';

import HomePage from './Components/WebPages/HomePage.js'

function App() {
  return (
    // <div className="App">
    <Router>
      <Switch>
        <Route path = '/'>
          <HomePage/>
        </Route>

      </Switch>
    </Router>
      // <ButtonAppBar/>
    // </div>
  );
}

export default App;
