// React
import React, { Component } from 'react'

// MUI
import { withStyles } from '@material-ui/core/styles';

import SideBar from '../TopBar/SideBar'

const styles = theme => ({
    
})

class StudentsPage extends Component {

    componentDidMount() {
        document.title = "TutorGang | MyStudents"
    }

    render() {
        return (
            <div>
                <SideBar />
            </div>
        )
    }
}

export default withStyles(styles)(StudentsPage);
