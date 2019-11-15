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
import TimeSlotPage from './Components/WebPages/TimeSlotPage'
import TutoringSessionPage from './Components/WebPages/TutoringSessionPage'
import SettingsPage from './Components/WebPages/Settings'
import ErrorPage502 from './Components/ErrorPages/ErrorPage502'

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
        <Route path="/students" component={StudentsPage} />
        <Route path="/courses" component={CoursesPage} />
        <Route path="/timeslots" component={TimeSlotPage} />
        <Route path="/tutoringsessions" component={TutoringSessionPage} />
        <Route path='/settings' component={SettingsPage} />
        <Route path="/502" component={ErrorPage502} />
      </Switch>
    </Router>
  );
}

export default App;
