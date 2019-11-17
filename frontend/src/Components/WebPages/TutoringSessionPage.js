// React
import React, { Component } from 'react';
import PropTypes from 'prop-types';

// MUI
import { withStyles } from '@material-ui/core/styles';
import SideBar from '../TopBar/SideBar';
import { Button } from '@material-ui/core';
import TextField from '@material-ui/core/TextField';
import InputLabel from '@material-ui/core/InputLabel';
import MenuItem from '@material-ui/core/MenuItem';
import FormControl from '@material-ui/core/FormControl';
import Select from '@material-ui/core/Select';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';

// Other
import MaskedInput from 'react-text-mask';
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

function createData(sessionDate, roomNumber, bookingId, startTime, endTime) {
    return { sessionDate, roomNumber, bookingId, startTime, endTime };
}

const rows = [
    createData('12/11/19', '2511', '5', '10:00', '11:00'),
    createData('25/05/20', '3123', '11', '13:00', '15:00'),
    createData('08/02/20', '1252', '8', '17:00', '19:00'),
];

class TutoringSessionPage extends Component {

    componentDidMount() {
        document.title = "TutorGang | My Sessions"
    }

    render() {

        const { classes } = this.props;

        return (
            <div>
                <SideBar />
                <div className={classes.mainContainer}>
                    <div>
                        <h1 style={{ marginTop: 0 }}>My Sessions</h1>
                    </div>
                    <div className={classes.currentContainer}>
                        <Paper className={classes.tableContainer}>
                            <Table>
                                <TableHead>
                                    <TableRow>
                                        <TableCell align="left">Date</TableCell>
                                        <TableCell align="left">Room Number</TableCell>
                                        <TableCell align="left">Booking ID</TableCell>
                                        <TableCell align="left">Start Time</TableCell>
                                        <TableCell align="left">End Time</TableCell>
                                    </TableRow>
                                </TableHead>
                                <TableBody>
                                    {rows.map(row => (
                                        <TableRow key={row.name}>
                                            <TableCell align="left">{row.sessionDate}</TableCell>
                                            <TableCell align="left">{row.roomNumber}</TableCell>
                                            <TableCell align="left">{row.bookingId}</TableCell>
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

TutoringSessionPage.propTypes = {
    classes: PropTypes.object.isRequired,
}

export default withStyles(styles)(TutoringSessionPage);
