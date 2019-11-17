// React
import React from 'react';
import PropTypes from 'prop-types';

// Components
import TopBar from '../TopBar/TopBar.js';

// Material-UI
import { withStyles } from '@material-ui/core/styles';

import mainImage from '../Images/tutor.jpg'

const styles = () => ({
    mainContainer: {
        // display: 'grid',
        // gridTemplateRows: '0vh 0px 95vh 1fr auto',
        // maxWidth: '100vw',
    },
    mainImageContainer: {
        height:'100vh',
        width:'100vw',
        backgroundImage: `url(${mainImage})`,
        backgroundSize:'cover',
        backgroundPosition:'center',
        maxWidth:'100%',
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'center',
    },
    blackLayer: {
        backgroundColor: 'rgba(0,0,0, 0.5)',/* Black w/opacity/see-through */
        color: 'white',
        fontWeight: 'bold',
        display: 'flex',
        zIndex: '2',
        width: '100%',
        alignItems:'center',
        justifyContent:'center',
        margin:'auto',
        height:'100vh',
    },
    mainImageText:{
        color: 'white', 
        fontSize: 25,
        lineHeight:'3',
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
                                We're looking for passionate tutors to help teach the youth of tomorrow. <br />
                                Share your passion with the world and sign up today!
                            </h1>
                        </div>

                        {/* INSERT BUTTON HERE TO SIGN UP */}
                    </div>
                </div>
            </div>
        );
    }
}

HomePage.propTypes = {
    classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(HomePage);
