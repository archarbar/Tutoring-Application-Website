import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import Typography from '@material-ui/core/Typography';
import Button from '@material-ui/core/Button';
import IconButton from '@material-ui/core/IconButton';
import MenuIcon from '@material-ui/icons/Menu';

import Logo from './Images/logo.png'

const useStyles = makeStyles(theme => ({
  root: {
    flexGrow: 1,
  },
  menuButton: {
    marginRight: theme.spacing(1),
  },
  title: {
    flexGrow: 1,
  },
  button: {
    marginLeft: theme.spacing(2),
  }
}));

export default function ButtonAppBar() {
  const classes = useStyles();

  return (
    <div className={classes.root}>
      <AppBar position="static">
        <Toolbar>
           <IconButton onClick={() => redirectTo("/")} edge="start" className={classes.menuButton} color="inherit" aria-label="menu">
            <img src = {Logo} style={{width:35, height:35}} />
          </IconButton>
          <Typography variant="h6" className={classes.title}>
            TutorMe!
          </Typography>
          <Button  onClick={() => redirectTo("/register")} className = {classes.button} color="inherit">Apply</Button>
          <Button onClick={() => redirectTo("/login")} color="inherit">Login</Button>
        </Toolbar>
      </AppBar>
    </div>
  );
}


function redirectTo(name){
  window.location.pathname = name;
}