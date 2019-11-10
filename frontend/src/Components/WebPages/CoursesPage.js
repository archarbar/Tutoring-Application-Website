// React
import React, { Component } from 'react'

// MUI
import { withStyles } from '@material-ui/core/styles';

import SideBar from '../TopBar/SideBar'

const styles = theme => ({
    
})

class CoursesPage extends Component {

    componentDidMount() {
        document.title = "TutorGang | MyCourses"
    }

    render() {
        return (
            <div>
                <SideBar />
            </div>
        )
    }
}

export default withStyles(styles)(CoursesPage);
