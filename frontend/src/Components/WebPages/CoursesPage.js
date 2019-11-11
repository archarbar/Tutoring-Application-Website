// React
import React, { Component } from 'react'
import PropTypes from 'prop-types';

// MUI
import { withStyles } from '@material-ui/core/styles';

import SideBar from '../TopBar/SideBar'

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

class CoursesPage extends Component {

    componentDidMount() {
        document.title = "TutorGang | MyCourses"
    }

    render() {

        const { classes } = this.props;

        return (
            <div>
                <SideBar />
                <div className={classes.mainContainer}>
                    <div>
                        <h1 style={{ marginTop: 0 }}>My Courses</h1>
                    </div>
                    <div className={classes.courseContainer}>
                        <h1>LIST COURSES HERE</h1>
                    </div>
                </div>
            </div>
        )
    }
}

CoursesPage.propTypes = {
    classes: PropTypes.object.isRequired,
}

export default withStyles(styles)(CoursesPage);
