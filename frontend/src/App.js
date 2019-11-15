import React from 'react';
import './App.css';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';

// Components
import HomePage from './Components/WebPages/HomePage.js'
import RegisterPage from './Components/WebPages/RegisterPage'
import LoginPage from './Components/WebPages/LoginPage'
import Dashboard from './Components/WebPages/Dashboard'
import CoursesPage from './Components/WebPages/CoursesPage'
import StudentsPage from './Components/WebPages/StudentsPage'
import SettingsPage from './Components/WebPages/Settings'

function App() {
  return (
    <Router>
      <Switch>
        <Route exact path='/'>
          <HomePage />
        </Route>
        <Route exact path="/register" component={RegisterPage} />
        <Route exact path="/login" component={LoginPage} />
        <Route path="/dashboard" component={Dashboard} />
        <Route path="/students" component={CoursesPage} />
        <Route path="/courses" component={StudentsPage} />
        <Route path = '/settings' component={SettingsPage} />
      </Switch>
    </Router>
  );
}

export default App;
