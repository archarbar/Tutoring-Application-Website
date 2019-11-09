// React
import React from 'react';
import PropTypes from 'prop-types';
import { NavLink } from 'react-router-dom';

// Components
import TopBar from '../TopBar.js';

// Material-UI
import { withStyles } from '@material-ui/core/styles';
import Fab from '@material-ui/core/Fab';

import mainImage from '../Images/tutor.jpg'

const styles = () => ({
    mainContainer: {
        // display: 'grid',
        // gridTemplateRows: '0vh 0px 95vh 1fr auto',
        // maxWidth: '100vw',
    },
    // mainImageContainer: {
    //     gridRowStart: 3,
    //     // backgroundImage: `url(${mainImage})`,
    //     opacity:'1',
    //     backgroundPosition: 'center',
    //     backgroundSize: 'cover',
    //     // backgroundColor: 'rgba(1, 1, 1, 0)',
    //     display: 'flex',
    //     alignItems: 'center',
    //     // flexDirection: 'column',
    //     justifyContent: 'center',
    // },
    mainImage: {
        position:'relative',
        width:'100vw',

        backgroundColor: 'rgba(1,1,1, 0.8)',
        // textAlign: 'center',
    },
    // titleContainer: {
    //     gridRowStart: 1,
    //     display: 'flex',
    //     justifyContent: 'center',
    //     fontStyle: 'italic',
    //     fontSize: 20,
    //     padding: '0 160px',
    //     gridColumnStart: 'span 3',
    //     maxWidth: '100vw',
    // },
    // mainImageText:{
    //     position:'absolute',
    //     // gridRowStart:,
    //     width:'80vw',
    //     height:'40vh',
    //     color: 'white', 
    //     fontSize: 30,
    //     margin:'auto',
    //     textAlign: 'center',
    //     alignItems:'center',
    //     lineHeight:'3',
    //     backgroundColor: 'rgba(1,1,1, 0.8)',

    //     // marginLeft: '20px'
    // }
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
        document.title = "HomePage"
    }

    render() {

        const { classes } = this.props;
        const { width } = this.state;

        return (
            <div>
                <TopBar />
                <div className={classes.mainContainer}>
                    <div
                        className={classes.mainImageContainer}
                    >
                        <div className={classes.mainImageDiv}>
                            <img src={mainImage}  className = {classes.mainImage} alt="Tutor session" />

                            <h1 className={classes.mainImageText}>
                                We're looking for passionate tutors to help teach the youth of tomorrow. <br/>
                                Share your passion with the world and sign up today! 
                            </h1>
                            {/* INSERT BUTTON HERE TO SIGN UP */}
                        </div>
                        <div>

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

export default withStyles(styles)(HomePage);

