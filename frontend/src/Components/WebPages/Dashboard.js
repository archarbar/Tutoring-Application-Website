// React
import React, { Component } from 'react'
import PropTypes from 'prop-types';

// MUI
import { withStyles } from '@material-ui/core/styles';

import API from '../Utilities/API';

import SideBar from '../TopBar/SideBar';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';


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
    currentContainer: {
        minHeight: 200,
        display: 'flex',
        alignItems: 'space-around',
        marginTop: 20,
        width: '80%',
        flexDirection: 'column',
    },
    tableContainer: {
        borderStyle: 'solid',
        borderColor: "#DADADA",
        borderWidth: 2,
        borderRadius: 5,
        boxShadow: 'none',
    },
})

function createData(courseName, studentName, sessionDate, startTime, endTime) {
    return { courseName, studentName, sessionDate, startTime, endTime };
}

const rows = [
    createData('ECSE321', 'Victor Zhong', '12/11/19', '10:00', '11:00'),
    createData('ECSE211', 'Michael Li', '25/05/20', '13:00', '15:00'),
    createData('ACC361', 'William Zhang', '08/02/20', '17:00', '19:00'),
];

class Dashboard extends Component {
    constructor(props){
        super(props)
        this.state = {
            tutorId : '',
            tutorName: ''
        }
    }
    componentDidMount() {
        document.title = "BigBrain Tutoring | Dashboard";
        var id;
        if(this.props.tutorId !== '0'){
            id = this.props.tutorId;
        }
        else{
            id = localStorage.getItem('tutorId')
        }
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
                        <h1 style={{ marginTop: '6vh' }}>Pending Bookings</h1>

                    </div>
                    <div className={classes.currentContainer}>
                        <Paper className={classes.tableContainer}>
                            <Table>
                                <TableHead>
                                    <TableRow>
                                        <TableCell align="left">Course Name</TableCell>
                                        <TableCell align="left">Student(s) Name(s)</TableCell>
                                        <TableCell align="left">Session Date</TableCell>
                                        <TableCell align="left">Start Time</TableCell>
                                        <TableCell align="left">End Time</TableCell>
                                    </TableRow>
                                </TableHead>
                                <TableBody>
                                    {rows.map(row => (
                                        <TableRow key={row.name}>
                                            <TableCell align="left">{row.courseName}</TableCell>
                                            <TableCell align="left">{row.studentName}</TableCell>
                                            <TableCell align="left">{row.sessionDate}</TableCell>
                                            <TableCell align="left">{row.startTime}</TableCell>
                                            <TableCell align="left">{row.endTime}</TableCell>
                                        </TableRow>
                                    ))}
                                </TableBody>
                            </Table>
                        </Paper>
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
