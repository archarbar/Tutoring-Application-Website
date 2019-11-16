// React
import React from 'react';
import PropTypes from 'prop-types';
import { NavLink, Redirect, withRouter } from 'react-router-dom';

// Material-UI
import { withStyles } from '@material-ui/core/styles';
import TextField from '@material-ui/core/TextField';
import Fab from '@material-ui/core/Fab';

// Other
import API from '../Utilities/API';

const styles = theme => ({
    mainContainer: {
        borderStyle: 'solid',
        borderWidth: 2,
        borderRadius: 40,
        borderColor: '#DADADA',
        width: '35vw',
        minWidth: 500,
        minHeight: '38vh',
        marginLeft: 'auto',
        marginRight: 'auto',
        marginTop: '10vh',
        marginBottom: '10vh',
        textAlign: 'center',
        boxShadow: "0 6px 8px rgba(0,0,0,0.20), 0 1px 2px rgba(0,0,0,0.24)",
    },
    container: {
        display: 'inline-block',
        width: '75%'
    },
    textField: {
        marginTop: 20,
        width: '21vw',
        minWidth: 300,
        display: 'block',
        marginLeft: 'auto',
        marginRight: 'auto'
    },
    mobileTextField: {
        marginTop: 20,
        width: '60vw',
        display: 'inline-block'
    },
    button: {
        width: '12vw',
        display: 'block',
        marginTop: '8%',
        marginLeft: 'auto',
        marginRight: 'auto',
        minWidth: 250
    },
    p: {
        marginTop: '8%',
        marginBottom: '8%',
        display: 'inline-block',
        fontSize: 14,
    },
    signin: {
        color: '#3f51b5',
        fontWeight: 630,
        display: 'inline'
    },
    title: {
        display: 'inline-block',
        marginBottom: '8%',
        marginTop: '8%',
        width: '100%',
        color: '#3f51b5'
    },
    error: {
        margin: 0,
        fontSize: 14
    },
    forgotPassword: {
        fontSize: 14,
        textAlign: 'left',
        width: '21vw',
        color: "#9D1010",
        fontWeight: 630,
        marginLeft: 45
    },
});

const onMouseOut = event => {
    const el = event.target;
    el.style.color = '#3f51b5';
}

const onMouseOver = event => {
    const el = event.target;
    el.style.color = "#081769"
}

class LoginForm extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            email: '',
            password: "",
            firstError: false,
            myText: "",
            emailError: false,
            passwordError: false,
            loggingIn: false,
            networkError: false,
            redirect: false,
        }
        this.handleEvent = this.handleEvent.bind(this);
        this.handleClick = this.handleClick.bind(this);
        this.handleKeyPress = this.handleKeyPress.bind(this)
    }

    componentDidMount() {
        document.title = "TutorGang | Login"
    }

    handleEvent = e => {
        this.setState({ [e.target.name]: e.target.value })
    }

    handleKeyPress = e => {
        if (e.which === 13) {
            this.handleClick();
        }
    }

    handleClick = () => {
        var regexForEmail = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
        var isEmailCorrect = this.state.email.match(regexForEmail)
        var isPasswordCorrect = this.state.password.length !== 0
        if (isPasswordCorrect && isEmailCorrect) {
            this.setState({
                emailError: !isEmailCorrect,
                passwordError: !isPasswordCorrect,
                loggingIn: true,
            })
            const { email, password } = this.state;

            API.loginTutor({ 'email': email, 'password': password }).then(res => {
                if (res.status == 200) {
                    this.props.history.push('/dashboard');
                    window.sessionStorage.setItem("sessionInfo", JSON.stringify(res.data));
                }
                else {
                    this.setState({ networkError: true });
                }
            }).catch(error => {
                console.log(error);
            })
        }
        else {
            this.setState({
                emailError: !isEmailCorrect,
                passwordError: !isPasswordCorrect,
                loggingIn: false,
                password: "",
            })
        }
    }

    render() {

        const { classes, lang } = this.props;
        const { loggingIn, redirect } = this.state;

        if (redirect) {
            return <Redirect to='/dashboard' />
        }

        return (
            <div className={classes.mainContainer}>
                <form className={classes.container} noValidate>
                    <h1 className={classes.title}>
                        Login
          </h1>
                    {/* ErrorHandling for invalid email and empty password */}
                    {this.state.firstError ? <p className={classes.error}>{this.state.myText}</p> : null}
                    {this.state.emailError ?
                        <p className={classes.error}>
                            A valid email is required
            </p> : null}
                    <TextField
                        id="outlined-email-input"
                        label={lang === 'en' ? "Email Address" : "Courriel"}
                        className={classes.textField}
                        type="text"
                        name="email"
                        margin="normal"
                        autoComplete="username"
                        variant="outlined"
                        fullWidth
                        error={this.state.emailError}
                        value={this.state.email}
                        onChange={e => this.handleEvent(e)}
                        onKeyPress={e => this.handleKeyPress(e)}
                    />
                    {this.state.passwordError ?
                        <p className={classes.error}>
                            A valid password is required
            </p> : null}
                    <TextField
                        id="outlined-email-input"
                        label={lang === 'en' ? "Password" : "Mot de passe"}
                        className={classes.textField}
                        type="password"
                        name="password"
                        margin="normal"
                        autoComplete="current-password"
                        variant="outlined"
                        fullWidth
                        error={this.state.passwordError}
                        value={this.state.password}
                        onChange={e => this.handleEvent(e)}
                        onKeyPress={e => this.handleKeyPress(e)}
                    />
                    <Fab
                        variant="extended"
                        size="large"
                        color="primary"
                        aria-label="Add"
                        className={classes.button}
                        fullWidth
                        onClick={this.handleClick}
                        backgroundColor='#AC101D'
                        style={loggingIn ? { opacity: 0.7 } : null}
                    >
                        {loggingIn ?
                            'Signing in...' :
                            'Login'}
                    </Fab>
                    <div className={classes.p}>
                        <p>
                            Don't have an account yet?
                              &nbsp;
              <NavLink to="/register" style={{ textDecoration: 'none' }}>
                                <p className={classes.signin} onMouseEnter={event => onMouseOver(event)} onMouseOut={event => onMouseOut(event)}>
                                    Register
                                </p>
                            </NavLink>
                        </p>
                    </div>
                </form>
            </div>
        )
    }
}

LoginForm.propTypes = {
    classes: PropTypes.object.isRequired,
};

export default withRouter(withStyles(styles)(LoginForm));
