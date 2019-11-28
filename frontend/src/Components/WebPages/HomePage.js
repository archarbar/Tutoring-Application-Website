// React
import React from 'react';
import PropTypes from 'prop-types';
import { withRouter, NavLink } from 'react-router-dom';

// Components
import TopBar from '../TopBar/TopBar.js';

// Material-UI
import { withStyles } from '@material-ui/core/styles';

import mainImage from '../Images/tutor.jpg'
import { Button } from '@material-ui/core';

const styles = () => ({
    mainImageContainer: {
        height: '93.5vh',
        width: '100vw',
        backgroundImage: `url(${mainImage})`,
        backgroundSize: 'cover',
        backgroundPosition: 'center',
        maxWidth: '100%',
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'center',
    },
    blackLayer: {
        backgroundColor: 'rgba(0,0,0, 0.5)',
        color: 'white',
        fontWeight: 'bold',
        display: 'flex',
        flexDirection: 'column',
        zIndex: '2',
        width: '100%',
        alignItems: 'center',
        justifyContent: 'center',
        margin: 'auto',
        height: '93.5vh',
    },
    mainImageText: {
        color: 'white',
        fontSize: 25,
        lineHeight: '3',
        textAlign: 'center',
    }
});

class HomePage extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            width: window.innerWidth,
        }
    }

    componentDidMount() {
        window.addEventListener('resize', this.updateDimensions);
        document.title = "BigBrain Tutoring | Home"
    }

    handleClick() {
        this.props.history.push('/register');
    }

    render() {

        const { classes } = this.props;

        return (
            <div>
                <TopBar />
                <div className={classes.mainContainer}>
                    <div className={classes.mainImageContainer}>
                        {/* <img src={mainImage}  className = {classes.mainImage} alt="Tutor session" /> */}
                        <div className={classes.blackLayer}>
                            <h1 className={classes.mainImageText}>
                                We're looking for passionate BigBrain tutors to help teach the youth of tomorrow. <br />
                                Share your passion with the world and sign up today!
                            </h1>
                            <Button
                                variant="contained"
                                color="primary"
                                style={{
                                    width: 150,
                                    height: 40
                                }}
                            >
                                <NavLink to='/register' style={{ textDecoration: 'none', color: '#FFFFFF' }}>SIGN UP</NavLink>
                            </Button>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

HomePage.propTypes = {
    classes: PropTypes.object.isRequired,
};

export default withRouter(withStyles(styles)(HomePage));
