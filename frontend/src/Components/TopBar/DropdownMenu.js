//React

import React from 'react';
import PropTypes from 'prop-types';
import { Link } from 'react-router-dom';

//MUI

import { withStyles } from '@material-ui/core/styles';
import Popper from '@material-ui/core/Popper';
import Grow from '@material-ui/core/Grow';
import Paper from '@material-ui/core/Paper';
import MenuItem from '@material-ui/core/MenuItem';
import MenuList from '@material-ui/core/MenuList';
import ListItemIcon from '@material-ui/core/ListItemIcon';

//Icons

import SettingsIcon from '@material-ui/icons/Settings';
import LogoutIcon from '@material-ui/icons/ExitToApp';

const styles = theme => ({
  dropdownMenu: {
    width: 160,
    padding: 10
  },
  companyChoice: {
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
    height: 40,
    width: 'auto'
  },
  otherIcon: {
    height: 35,
  },
})

class DropdownMenu extends React.Component {

  handleHome = () => {
    this.props.changeRailvision();
    this.props.changeMainmenu();
    this.props.history.push('/');
  }

  handleLogout = () => {
  }

  render() {
    const { classes, openProfile, anchorEl, } = this.props
    return (

      <div>
        <Popper open={openProfile} anchorEl={anchorEl} transition disablePortal>
          {({ TransitionProps, placement }) => (
            <Grow
              {...TransitionProps}
              id="menu-list-grow"
              style={{ transformOrigin: placement === 'bottom' ? 'center top' : 'center bottom' }}
            >
              <Paper>
                <MenuList className={classes.dropdownMenu}>
                  <MenuItem
                    style={{ fontSize: 15 }}
                    component={Link} 
                    to="/settings"
                  >
                    <div className={classes.companyChoice}>
                      <ListItemIcon>
                        <SettingsIcon
                          className={classes.otherIcon}
                        />
                      </ListItemIcon>
                      Settings
                    </div>
                  </MenuItem>
                  <MenuItem
                    onClick={this.handleLogout}
                    style={{ fontSize: 15 }}
                  >
                    <div className={classes.companyChoice}>
                      <ListItemIcon>
                        <LogoutIcon
                          className={classes.otherIcon}
                        />
                      </ListItemIcon>
                      Logout
                    </div>
                  </MenuItem>
                </MenuList>
              </Paper>
            </Grow>
          )}
        </Popper>
      </div>

    )
  }
}

DropdownMenu.propTypes = {
  classes: PropTypes.object.isRequired,
  openDrawer: PropTypes.func,
  closeDrawer: PropTypes.func,
  changeLanguage: PropTypes.func,
  lang: PropTypes.string.isRequired,
  changeMainmenu: PropTypes.func.isRequired,
  changeLogout: PropTypes.func.isRequired
};

export default (withStyles(styles, { withTheme: true })(DropdownMenu));
