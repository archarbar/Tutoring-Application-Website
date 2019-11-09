// React
import React from 'react';
import PropTypes from 'prop-types';

// Components
import TopBar from '../TopBar.js';

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
        height:'90vh',
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
        height:'90vh',
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
        document.title = "HomePage"
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


// import React from 'react';
// import './App.css';
// import SimpleAppBar from './Components/Appbar/appbar.js'
// import { makeStyles } from '@material-ui/core/styles';
// import backgroundPictureOne from './Pictures/wallpaper.jpg'
// import Button from '@material-ui/core/Button';


// const useStyles = makeStyles({
//   firstBox:{
//     height:'90vh',
//     width:'100vw',
//     backgroundImage: `url(${backgroundPictureOne})`,
//     backgroundSize:'cover',
//     backgroundPosition:'center',
//     // filter:'blur(3px)',
//     maxWidth:'100%',
//   },
//   textFirstBox:{
//     backgroundColor: 'rgb(0,0,0)', /* Fallback color */
//     backgroundColor: 'rgba(0,0,0, 0.4)',/* Black w/opacity/see-through */
//     color: 'white',
//     fontWeight: 'bold',
//     position: 'absolute',
//     top: '20vh',
//     zIndex: '2',
//     width: '100vw',
//     textAlign: 'center',
//     maxWidth:'100%',
//   },
//   title:{
//     fontSize:'50px',
//     paddingTop:'20px',
//   },
//   text:{
//     fontSize:'24px',
//     padding:'20px',
//     paddingTop:'0'
//   },
//   secondBox:{
//     height:'90vh',
//     width:'90vw',
//     margin:'auto',
//     backgroundColor:'yellow',
//     backgroundSize:'cover',
//     backgroundPosition:'center',
//   },
//   button:{
//     marginBottom:'30px',
//   }
// });


// function App() {
//   const classes = useStyles();
//   return (
//     <div className="App">
//       <SimpleAppBar/>
//       <div className={classes.firstBox}></div>
//       <div className={classes.textFirstBox}>
//         <p className={classes.title}>Our solution for affordable housing.</p>
//         <p className={classes.text}>Affordable housing means reducing the complexity of mortgages and facilitating the transition from tenant to owner</p>
//         <Button variant="contained" className={classes.button}>
//           Learn more
//         </Button>
//       </div>
//       <div>
//         <p>hello</p>
//         <p>hello</p>
//         <p>hello</p>
//         <p>hello</p>
//       </div>
//       {/* <div className={classes.secondBox}>

//       </div> */}

//     </div>
//   );
// }

// export default App;
