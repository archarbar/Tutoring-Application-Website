// React

import React from 'react';
import PropTypes from 'prop-types';
import { Link } from 'react-router-dom';

// Material-UI

import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import Typography from '@material-ui/core/Typography';
import IconButton from '@material-ui/core/IconButton';
import { withStyles } from '@material-ui/core/styles';
import Drawer from '@material-ui/core/Drawer';
import ListItem from '@material-ui/core/ListItem';
import ListItemIcon from '@material-ui/core/ListItemIcon';
import ListItemText from '@material-ui/core/ListItemText';
import MenuList from '@material-ui/core/MenuList';
import ClickAwayListener from '@material-ui/core/ClickAwayListener';

// Icons

import MenuIcon from '@material-ui/icons/Menu';
import SettingsIcon from '@material-ui/icons/Settings';
import MoreIcon from '@material-ui/icons/MoreHoriz';
import DashboardIcon from '@material-ui/icons/Dashboard';
import MenuBookIcon from '@material-ui/icons/MenuBook';
import AccessTimeIcon from '@material-ui/icons/AccessTime';
import ApartmentIcon from '@material-ui/icons/Apartment';

// Other

import classNames from 'classnames';
import DropdownMenu from './DropdownMenu';

var drawerWidth = 220;
const styles = theme => ({
  root: {
    display: 'flex',
    flexGrow: 1,
  },
  appBar: {
    zIndex: theme.zIndex.drawer + 1,
    margin: 0
  },
  drawer: {
    width: drawerWidth,
    flexShrink: 0,
    whiteSpace: 'nowrap',
  },
  content: {
    flexGrow: 2,
    padding: theme.spacing(3),
  },
  companyTitle: {
    flexGrow: 1,
    textAlign: 'left',
    fontWeight: 520,
    paddingLeft: 70,
  },
  menuButton: {
    marginRight: 20
  },
  button: {
    marginRight: 15,
    marginLeft: 15,
  },
  drawerOpen: {
    width: drawerWidth,
    transition: theme.transitions.create('width', {
      easing: theme.transitions.easing.sharp,
      duration: theme.transitions.duration.enteringScreen,
    }),
  },
  drawerClose: {
    transition: theme.transitions.create('width', {
      easing: theme.transitions.easing.sharp,
      duration: theme.transitions.duration.leavingScreen,
    }),
    overflowX: 'hidden',
    [theme.breakpoints.up('sm')]: {
      width: theme.spacing(9) + 20,
    },
  },
  list1: {
    marginTop: 80,
    marginLeft: 20,
    marginRight: 20,
  },
  paper: {
    marginRight: theme.spacing(2),
  },
  topButtons: {
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
  }
});

class SideBar extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      openProfile: false,
      openAlert: false,
      currentUser: null,
      userRoles: null,
      company: null,
      currentRoles: [],
      currentCompanies: [],
      isDesktop: false,
      sidebarOpen: false,
      openSnack: false,
    }
    this.handleToggle = this.handleToggle.bind(this)
    this.handleChange = this.handleChange.bind(this);
  }

  handleToggle = event => {
    this.setState(state => ({ sidebarOpen: !state.sidebarOpen }));
  }

  handleOpenProfile = () => {
    this.setState({ openProfile: true, openAlert: false });
  };

  handleCloseProfile = () => {
    if (this.state.openProfile === true) {
      this.setState({ openProfile: false });
    }
  };

  handleSettings = () => {
    this.setState({ openProfile: false });
    this.setState({ openAlert: false });
    this.props.history.push('/settings')
  }

  handleChange = (event) => {
    this.props.changeLanguage(event.currentTarget.value);
  }

  render() {
    const { classes } = this.props;
    const { openProfile } = this.state;

    return (
      <div className={classes.root}>
        <AppBar className={classNames(classes.appBar, {
          [classes.appBarShift]: this.state.sidebarOpen,
        })}>
          <Toolbar>
            <IconButton
              color="inherit"
              aria-label="Open drawer"
              onClick={this.handleToggle}
              className={this.state.isDesktop ? classNames(classes.menuButton, {
                [classes.hide]: this.state.sidebarOpen,
              }) : classes.menuButtomMobile}
            >
              <MenuIcon />
            </IconButton>
            <Typography variant="h6" color="inherit" className={classes.companyTitle}>
              BigBrain Tutoring
            </Typography>
            <div className={classes.topButtons}>
              <ClickAwayListener onClickAway={this.handleCloseProfile}>
                <IconButton
                  buttonRef={node => {
                    this.anchorEl = node;
                  }}
                  aria-owns={openProfile ? 'menu-list-grow' : undefined}
                  aria-haspopup="true"
                  onClick={!openProfile ? this.handleOpenProfile : this.handleCloseProfile}
                  color="inherit"
                  style={{ margin: 0 }}
                >
                  <MoreIcon />
                </IconButton>
              </ClickAwayListener>
              <DropdownMenu
                currentCompanies={this.state.currentCompanies}
                openProfile={this.state.openProfile}
                anchorEl={this.anchorEl}
                handleClickRailvision={this.handleClickRailvision}
                openSnack={this.openSnack}
              />
            </div>
          </Toolbar>
        </AppBar>
        <Drawer
          variant="permanent"
          className={classNames(classes.drawer, {
            [classes.drawerOpen]: this.state.sidebarOpen,
            [classes.drawerClose]: !this.state.sidebarOpen,
          })}
          classes={{
            paper: classNames({
              [classes.drawerOpen]: this.state.sidebarOpen,
              [classes.drawerClose]: !this.state.sidebarOpen,
            }),
          }}
        >
          <MenuList className={classes.list1}>
            <ListItem button component={Link} to="/dashboard">
              <ListItemIcon> <DashboardIcon /> </ListItemIcon>
              <ListItemText primary={'Dashboard'} />
            </ListItem>
            <ListItem button component={Link} to="/courses">
              <ListItemIcon><MenuBookIcon /> </ListItemIcon>
              <ListItemText primary={'My Courses'} />
            </ListItem>
            <ListItem button component={Link} to="/tutoringsessions">
              <ListItemIcon><ApartmentIcon /> </ListItemIcon>
              <ListItemText primary={'My Sessions'} />
            </ListItem>
            <ListItem button component={Link} to="/timeslots">
              <ListItemIcon><AccessTimeIcon /> </ListItemIcon>
              <ListItemText primary={'My TimeSlots'} />
            </ListItem>
            <ListItem button component={Link} to="/settings">
              <ListItemIcon><SettingsIcon /> </ListItemIcon>
              <ListItemText primary={'Settings'} />
            </ListItem>
          </MenuList>
        </Drawer>
      </div>
    );
  }
}

SideBar.propTypes = {
  classes: PropTypes.object.isRequired,
  theme: PropTypes.object.isRequired,
  openDrawer: PropTypes.func,
  closeDrawer: PropTypes.func.isRequired,
  changeLanguage: PropTypes.func.isRequired,
  lang: PropTypes.string.isRequired,
  changeMainmenu: PropTypes.func.isRequired,
  changeLogout: PropTypes.func.isRequired,
};

export default (withStyles(styles, { withTheme: true })(SideBar));
