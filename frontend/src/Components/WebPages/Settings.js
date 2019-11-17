// React
import React, { Component } from 'react';
import PropTypes from 'prop-types';

// Material-UI
import { withStyles } from '@material-ui/core/styles';
import Typography from '@material-ui/core/Typography';
import ExpandMoreIcon from '@material-ui/icons/ExpandMore';
import ExpansionPanel from '@material-ui/core/ExpansionPanel';
import ExpansionPanelDetails from '@material-ui/core/ExpansionPanelDetails';
import ExpansionPanelSummary from '@material-ui/core/ExpansionPanelSummary';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';

import SideBar from '../TopBar/SideBar'

const styles = theme => ({
    mainContainer: {
        minWidth: 'calc(100% - 94px)',
        minHeight: '100vh',
        // marginLeft: '92px',
        marginRight: 'auto',
        paddingTop: '65px',
        backgroundColor: '#F4F4F4',
    },
    welcomeBlock: {
        marginTop: '5vh',
        borderWidth: 1,
        borderStyle: 'solid',
        borderColor: "#DFDFDF",
        width: '80%',
        marginLeft: 'auto',
        marginRight: 'auto',
        backgroundColor: '#FFFFFF',
        boxShadow: "0 6px 8px rgba(0,0,0,0.20), 0 1px 2px rgba(0,0,0,0.24)",
    },
    subTitle: {
        width: '100%',
        textAlign: 'center',
        marginTop: '5vh',
        marginBottom: '4vh',
    },
    heading: {
        fontSize: theme.typography.pxToRem(15),
        flexBasis: '22vw',
        flexShrink: 0,
    },
    panel: {
        marginTop: '1%',
        width: '80%',
        marginLeft: 'auto',
        marginRight: 'auto',
        paddingBottom: '3%'
    },
    insidePassword: {
        marginTop: 0,
        display: 'block',
        width: '100%',
        marginLeft: 'auto',
        marginRight: 'auto',
        marginBottom: '1%',
        textAlign: 'center'
    },
    phoneDiv: {
        marginTop: 0,
        width: '100%',
        display: 'inline-block'
    },
    textField: {
        marginTop: 20,
        marginRight: 'auto',
        marginLeft: 'auto',
        width: '30vw',
        display: 'block'
    },
    textFieldPhone: {
        marginLeft: '5%',
        marginRight: '5%',
        width: '40%',
        display: 'inline-block'
    },
    button: {
        width: '16.5vw',
        marginTop: '3vh',
        display: 'inline-block'
    },
    phoneButton: {
        width: '30%',
        display: 'inline-block',
        marginLeft: '8%',
        marginTop: '2%',
        marginBottom: 'auto',
        marginRight: '10%'
    },
    forgotPassword: {
        marginTop: '3vh',
        width: '20%',
        marginLeft: '2.5%',
        marginRight: 'auto',
        fontWeight: 630,
        textDecoration: 'none',
        verticalAlign: 'middle'
    },
    name: {
        display: 'inline',
        height: '50px',
        lineHeight: '50px',
        marginLeft: '2.25%',
        color: 'black',
        textAlign: 'left',
        paddingRight: '28%',
        fontSize: 15,
    },
    email: {
        display: 'inline',
        height: '50px',
        lineHeight: '50px',
        marginLeft: '2.25%',
        color: 'black',
        textAlign: 'left',
        paddingRight: '28.5%',
        fontSize: 15,
    },
    button2: {
        width: 'auto',
        marginRight: '2vw',
        fontWeight: 445,
        textTransform: 'capitalize'
    },
})

class SettingsPage extends Component {
    constructor(props) {
        super(props)
        this.state = {
            currentName: '',
            firstName: 'William',
            lastName: 'Zhang',
            email: 'william.zhang@coolguy99.com',
            emailError:false,
            phoneNumber: '1AMC00L',
            newPhoneNumber: '',
            phoneError: false,
            currentUser: null,
            userRoles: null,
            userId: null,
            company: null,
            languages: [],
            oldPassword: '',
            newPassword: '',
            confirmNewPassword: '',
            isDesktop: false,
            currentCompanies: [],
            open: false,
            translatedMessage: '',
            // variant:'warning'
            passwordExpansion: false
        }
        this.handleEvent = this.handleEvent.bind(this)
        this.setSpan = this.setSpan.bind(this)
        this.handleClickPhone = this.handleClickPhone.bind(this)
        this.handleClickPassword = this.handleClickPassword.bind(this)
        this.changePasswordExpansion = this.changePasswordExpansion.bind(this)
    }


    handleCloseSnackBar = (event, reason) => {
        if (reason === 'clickaway') {
            return;
        }
        this.setState({ open: false });
    }

    handleEvent = e => {
        this.setState({ [e.target.name]: e.target.value })
    }
    handleClickCompany = event => {

    }

    changePasswordExpansion() {
        this.setState({
            passwordExpansion: !this.state.passwordExpansion
        })
    }

    handleClickPassword() {
        const newPassword = this.state.newPassword
        //Need to make all the checks as in registerform
        const confirmNewPassword = this.state.confirmNewPassword
        const oldPassword = this.state.oldPassword
        var isPasswordCorrect = newPassword.length >= 8;
        var isConfirmPasswordCorrect = newPassword === confirmNewPassword;

    }

    handleClickPhone() {
        const newPhoneNumber = this.state.newPhoneNumber
    }

    handleClick = event => {
        var index = event.currentTarget.value
        this.props.changeLanguage(this.state.languages[index].prefix);

    }

    componentDidMount() {
        window.addEventListener("resize", this.updatePredicate);
        document.title = "Settings"

    }

    setSpan() {
        this.props.history.push('/forgotpassword');
    }


    render() {

        const { classes, lang } = this.props;
        const { isDesktop, open, translatedMessage } = this.state;

        return (
            <div>
                <SideBar />
                <div
                    className={classes.mainContainer}
                    onClick={this.props.closeDrawer}
                >
                    <div className={classes.welcomeBlock}>
                        <h1
                            style={{
                                textAlign: 'center',
                                color: '#7E7E7E',
                                fontWeight: 520,
                            }}
                        >
                            Profile
                        </h1>
                    </div>

                    <Typography variant="h6" color="inherit" className={classes.subTitle}>
                        General user settings
                    </Typography>

                    <div className={classes.panel}>

                        <ExpansionPanel expanded={false}>
                            <ExpansionPanelSummary>
                                <Typography className={classes.heading}>
                                    Name
                </Typography>
                                <Typography className={classes.secondaryHeading}>
                                    {this.state.firstName} {this.state.lastName}
                                </Typography>
                            </ExpansionPanelSummary>
                        </ExpansionPanel>

                        <ExpansionPanel >
                            <ExpansionPanelSummary expandIcon={<ExpandMoreIcon />}>
                                <Typography className={classes.heading}>
                                    Email
                </Typography>
                                <Typography className={classes.secondaryHeading}>
                                    {this.state.email}
                                </Typography>
                            </ExpansionPanelSummary>
                            <ExpansionPanelDetails>
                                <div className={classes.phoneDiv}>
                                    <Typography>

                                        <form style={{ display: 'inline' }}>
                                            <TextField
                                                label={"New Email"}
                                                id="formatted-numberformat-input"
                                                margin="normal"
                                                variant="outlined"
                                                name="newPhoneNumber"
                                                autoComplete="tel"
                                                fullWidth
                                                className={classes.textFieldPhone}
                                                error={this.state.emailError}
                                                value={this.state.newEmail}
                                                onChange={e => this.handleEvent(e)}

                                            />
                                            <Button
                                                variant="contained"
                                                className={classes.phoneButton}
                                                color="primary"
                                                aria-label="Add"
                                                fullWidth
                                                onClick={this.handleClickPhone}
                                                size="large"
                                            >
                                                Change Email
                                            </Button>
                                        </form>

                                    </Typography>
                                </div>
                            </ExpansionPanelDetails>
                        </ExpansionPanel>

                        <ExpansionPanel>
                            <ExpansionPanelSummary expandIcon={<ExpandMoreIcon />}>
                                <Typography className={classes.heading}>
                                    Phone Number
                </Typography>
                                <Typography className={classes.secondaryHeading}>
                                    {this.state.phoneNumber}
                                </Typography>
                            </ExpansionPanelSummary>
                            <ExpansionPanelDetails>
                                <div className={classes.phoneDiv}>
                                    <Typography>

                                        <form style={{ display: 'inline' }}>
                                            <TextField
                                                label={"New Phone Number"}
                                                id="formatted-numberformat-input"
                                                margin="normal"
                                                variant="outlined"
                                                name="newPhoneNumber"
                                                autoComplete="tel"
                                                fullWidth
                                                className={classes.textFieldPhone}
                                                error={this.state.phoneError}
                                                value={this.state.newPhoneNumber}
                                                onChange={e => this.handleEvent(e)}

                                            />
                                            <Button
                                                variant="contained"
                                                className={classes.phoneButton}
                                                color="primary"
                                                aria-label="Add"
                                                fullWidth
                                                onClick={this.handleClickPhone}
                                                size="large"
                                            >
                                                Change Phone Number
                      </Button>
                                        </form>

                                    </Typography>
                                </div>
                            </ExpansionPanelDetails>
                        </ExpansionPanel>

                        <ExpansionPanel expanded={this.state.passwordExpansion} >
                            <ExpansionPanelSummary expandIcon={<ExpandMoreIcon />} onClick={this.changePasswordExpansion} >
                                <Typography className={classes.heading}>
                                    Password
                </Typography>
                                <Typography className={classes.secondaryHeading}>
                                    Keep your account secure by periodically changing your password!
                </Typography>
                            </ExpansionPanelSummary>
                            <ExpansionPanelDetails>
                                <div className={classes.insidePassword}>
                                    <TextField
                                        id="old-password"
                                        label={lang === 'en' ? 'Old Password' : 'Old Password'}
                                        className={classes.textField}
                                        type="password"
                                        name="oldPassword"
                                        margin="normal"
                                        autoComplete="current-password"
                                        variant="outlined"
                                        fullWidth
                                        value={this.state.oldPassword}
                                        onChange={e => this.handleEvent(e)}
                                    />
                                    <TextField
                                        id="new-password"
                                        label={lang === 'en' ? 'New Password' : 'New Password'}
                                        className={classes.textField}
                                        type="password"
                                        name="newPassword"
                                        margin="normal"
                                        autoComplete="new-password"
                                        variant="outlined"
                                        fullWidth
                                        value={this.state.newPassword}
                                        onChange={e => this.handleEvent(e)}
                                    />
                                    <TextField
                                        id="confirm-new-password"
                                        label={lang === 'en' ? 'Confirm New Password' : 'Confirm New Password'}
                                        className={classes.textField}
                                        type="password"
                                        name="confirmNewPassword"
                                        margin="normal"
                                        autoComplete="new-password"
                                        variant="outlined"
                                        fullWidth
                                        value={this.state.confirmNewPassword}
                                        onChange={e => this.handleEvent(e)}
                                    />
                                    <Button
                                        variant="contained"
                                        size="large"
                                        color="primary"
                                        aria-label="Add"
                                        className={classes.textField}
                                        fullWidth
                                        onClick={this.handleClickPassword}
                                    >
                                        Change Password
                                    </Button>
                                </ div>
                            </ExpansionPanelDetails>
                        </ExpansionPanel>
                    </div>

                    <Typography variant="h6" color="inherit" className={classes.subTitle}>
                        Current Courses
                    </Typography>

                    <div className={classes.panel2}>

                    </div>
                </div>

            </div>
        )
    }
}



SettingsPage.propTypes = {
    closeDrawer: PropTypes.func.isRequired,
};

export default withStyles(styles)(SettingsPage);
