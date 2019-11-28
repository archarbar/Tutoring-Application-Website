// React
import React, { Component } from 'react'
import PropTypes from 'prop-types';

// MUI
import { withStyles } from '@material-ui/core/styles';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';
import { Button } from '@material-ui/core';

// Components
import API from '../Utilities/API';
import SideBar from '../TopBar/SideBar';

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
        width: '90%',
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

class Dashboard extends Component {
    constructor(props) {
        super(props)
        this.state = {
            tutorId: '',
            tutorName: '',
            allBookings: [],
        }
        this.getAllBookings();
    }

    componentDidMount() {
        document.title = "BigBrain Tutoring | Dashboard";
        var id = localStorage.getItem('tutorId')
        API.getTutorById(id)
            .then(res => {
                this.setState({
                    tutorId: id,
                    tutorName: res.data.firstName + ' ' + res.data.lastName
                })
            })
    }

    getAllBookings() {
        var allData;
        API.getAllBookingsByTutor(localStorage.getItem('tutorId')).then(res => {
            console.log(JSON.stringify(res));
            allData = res.data.map(x => {
                return x;
            });
            this.setState({
                allBookings: allData
            });
        });
        return allData;
    }

    handleClickAccept(index) {
        const bookingId = this.state.allBookings[index].bookingId;
        API.acceptBooking(bookingId).then(res => {
            this.getAllBookings();
        }).catch(error => {
            console.log(error)
        })
    }

    handleClickDecline(index) {
        const bookingId = this.state.allBookings[index].bookingId;
        API.declineBooking(bookingId).then(res => {
            this.getAllBookings();
        }).catch(error => {
            console.log(error)
        })
    }

    render() {

        const { classes } = this.props;
        const { allBookings } = this.state;

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
                                        <TableCell align="left"></TableCell>
                                        <TableCell align="left"></TableCell>
                                    </TableRow>
                                </TableHead>
                                <TableBody>
                                    {allBookings.map((booking, index) => {
                                        return (
                                            <TableRow>
                                                <TableCell align="left">{booking.course}</TableCell>
                                                <TableCell align="left">{booking.studentName}</TableCell>
                                                <TableCell align="left">{booking.specificDate}</TableCell>
                                                <TableCell align="left">{booking.timeSlot.startTime}</TableCell>
                                                <TableCell align="left">{booking.timeSlot.endTime}</TableCell>
                                                <TableCell align="center">
                                                    <Button
                                                        id={index}
                                                        variant="contained"
                                                        color="primary"
                                                        onClick={() => this.handleClickAccept(index)}
                                                    >
                                                        Accept
                                                </Button>
                                                </TableCell>
                                                <TableCell align="center">
                                                    <Button
                                                        id={index}
                                                        variant="contained"
                                                        color="primary"
                                                        onClick={() => this.handleClickDecline(index)}
                                                    >
                                                        Decline
                                                </Button>
                                                </TableCell>
                                            </TableRow>
                                        )
                                    })}
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
