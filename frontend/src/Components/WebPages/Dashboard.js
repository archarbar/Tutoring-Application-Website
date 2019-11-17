// React
import React, { Component } from 'react'
import PropTypes from 'prop-types';

// MUI
import { withStyles } from '@material-ui/core/styles';

import SideBar from '../TopBar/SideBar'
import API from '../Utilities/API';

const styles = theme => ({
    mainContainer: {
        position: 'relative',
        display: 'flex',
        width: 'calc(100% - 92)',
        height: 'calc(100% - 64)',
        marginTop: 64,
        marginLeft: 92,
        padding: '5vh',
        flexDirection: 'column',
    },
    courseContainer: {
        border: '1px solid #000000',
        minHeight: 400,
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'center',
        marginTop: 20,
    }
})

class Dashboard extends Component {
    constructor(props){
        super(props)
        this.state = {
            tutorId : '',
            tutorName: ''
        }
    }
    componentDidMount() {
        document.title = "TutorGang | Dashboard";
        var id = localStorage.getItem('tutorId')
        API.getTutorById(id)
        .then(res =>{
            this.setState({
                tutorId : id,
                tutorName : res.data.firstName + ' ' + res.data.lastName
            })
        })

    }

    render() {

        const { classes } = this.props;
        return (
            <div>
                <SideBar />
                <div className={classes.mainContainer}>
                    <div>
                        <h1 style={{ marginTop: 0 }}>Welcome back, {this.state.tutorName} </h1>
                    </div>
                    <div className={classes.courseContainer}>
                        <h1>LIST NEW BOOKINGS HERE</h1>
                    </div>
                </div>
            </div>
        )
    }
}

Dashboard.propTypes = {
    classes: PropTypes.object.isRequired,
}

export default withStyles(styles)(Dashboard);
