// React
import React, { Component } from 'react';
import PropTypes from 'prop-types';

// MUI
import { withStyles } from '@material-ui/core/styles';
import SideBar from '../TopBar/SideBar';
import { Button } from '@material-ui/core';
import TextField from '@material-ui/core/TextField';
import MenuItem from '@material-ui/core/MenuItem';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';
import IconButton from '@material-ui/core/IconButton';
import DeleteIcon from '@material-ui/icons/Delete';
import Snackbar from '@material-ui/core/Snackbar';
import CloseIcon from '@material-ui/icons/Close';
import SnackbarContent from '@material-ui/core/SnackbarContent';

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
    newContainer: {
        minHeight: 140,
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'space-around',
        marginTop: 20,
        flexDirection: 'row',
        width: '80%',
        borderStyle: 'solid',
        borderColor: "#DADADA",
        borderWidth: 2,
        borderRadius: 5,
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
    innerContainer: {
        height: '100%',
    },
    textField: {
        minWidth: 150
    },
    button: {
        minWidth: 150,
        minHeight: 56,
    },
    error: {
        margin: 0,
        color: '#3f51b5',
        marginTop: 5
    },
})

function TextMaskCustom(props) {
    const { inputRef, ...other } = props;

    return (
        <MaskedInput
            {...other}
            ref={ref => {
                inputRef(ref ? ref.inputElement : null);
            }}
            mask={[/\d/, /\d/, ':', /\d/, /\d/]}
            placeholderChar={'\u2000'}
            keepCharPositions={true}
            placeholder="i.e. 12:30"
        />
    );
}

TextMaskCustom.propTypes = {
    inputRef: PropTypes.func.isRequired,
};

class TimeSlotPage extends Component {

    constructor(props) {
        super(props);
        this.state = {
            phoneNumber: '  :  ',
            startTime: 'hi',
            endTime: '',
            weekDay: '',
            startEmpty: false,
            endEmpty: false,
            dayEmpty: false,
            startError: true,
            endError: true,
            allTimeSlots: [],
            selected: [],
            open: false,
        }
        this.handleClick = this.handleClick.bind(this);
        this.handleClose = this.handleClose.bind(this);
        this.getAllTimeSlots();
    }

    componentDidMount() {
        document.title = "BigBrain Tutoring | My TimeSlots"
        this.getAllTimeSlots();
    }

    handleEvent = e => {
        this.setState({ [e.target.name]: e.target.value })
    }

    getAllTimeSlots() {
        var allData;
        API.getTimeSlotByTutor(localStorage.getItem('tutorId')).then(res => {
            console.log(JSON.stringify(res))
            allData = res.data.map(x => {
                return x;
            });
            this.setState({
                allTimeSlots: allData
            })
        })
        return allData;
    }

    handleClick() {
        const { startTime, endTime, weekDay } = this.state;
        var startEmpty = startTime === '';
        var endEmpty = endTime === '';
        var dayEmpty = weekDay === '';
        var id = localStorage.getItem('tutorId');
        if (startEmpty || endEmpty || dayEmpty) {
            this.setState({
                startEmpty: true,
                endEmpty: true,
                dayEmpty: true,
            })
        } else {
            var noErrorStart = (parseInt(this.state.startTime.slice(0, 2)) >= 9 && parseInt(this.state.startTime.slice(0, 2)) <= 22);
            var noErrorEnd = (parseInt(this.state.endTime.slice(0, 2)) >= 9 && parseInt(this.state.endTime.slice(0, 2)) <= 22);
            if (noErrorStart && noErrorEnd) {
                API.addTimeSlotForTutor({
                    'startTime': startTime + ':00',
                    'endTime': endTime + ':00',
                    'dayOfTheWeek': weekDay,
                    'tutorId': id,
                }).then(res => {
                    this.getAllTimeSlots();
                }).catch(error => {
                    console.log(error);
                })
            }
            else {
                this.setState({
                    startError: noErrorStart,
                    endError: noErrorEnd,
                    startEmpty: false,
                    endEmpty: false,
                    dayEmpty: false,
                })
            }
        }
    }

    handleClickDelete(index) {
        const timeSlotId = this.state.allTimeSlots[index].timeSlotId;
        const id = localStorage.getItem('tutorId');
        API.removeTimeSlotForTutor({
            'timeSlotId': timeSlotId,
            'tutorId': id
        }).then(res => {
            if (res.status === 500) {
                this.setState({
                    open: true,
                })
            }
            else {
                this.getAllTimeSlots();
            }
        }).catch(error => {
            console.log(error);
        });
    }

    handleClose = () => {
        this.setState({
            open: false,
        });
    };

    render() {

        const { classes } = this.props;
        const { endError, endEmpty, startError, startEmpty, dayEmpty, allTimeSlots, open } = this.state;

        return (
            <div>
                <SideBar />
                <div className={classes.mainContainer}>
                    <div>
                        <h1 style={{ marginTop: 0 }}>Add New TimeSlot</h1>
                    </div>
                    <div className={classes.newContainer}>
                        <div className={classes.innerContainer}>
                            {startEmpty ? <p className={classes.error}>The start time cannot be empty!</p> : null}
                            {!startError ? <p className={classes.error}>The time must be between 9:00 and 22:00!</p> : null}
                            <TextField
                                id="outlined-basic"
                                className={classes.textField}
                                label="Start time"
                                margin="normal"
                                variant="outlined"
                                name="startTime"
                                error={!startError || startEmpty}
                                value={this.state.startTime}
                                onChange={e => this.handleEvent(e)}
                                InputProps={{
                                    inputComponent: TextMaskCustom,
                                }}
                            />
                        </div>
                        <div className={classes.innerContainer}>
                            {endEmpty ? <p className={classes.error}>The end time cannot be empty!</p> : null}
                            {!endError ? <p className={classes.error}>The time must be between 9:00 and 22:00!</p> : null}
                            <TextField
                                id="outlined-basic"
                                className={classes.textField}
                                label="End time"
                                margin="normal"
                                variant="outlined"
                                name="endTime"
                                error={!endError || endEmpty}
                                value={this.state.endTime}
                                onChange={e => this.handleEvent(e)}
                                InputProps={{
                                    inputComponent: TextMaskCustom,
                                }}
                            />
                        </div>
                        <div className={classes.innerContainer}>
                            {dayEmpty ? <p className={classes.error}>The week day cannot be empty!</p> : null}
                            <TextField
                                labelWidth={25}
                                onChange={e => this.handleEvent(e)}
                                value={this.state.weekDay}
                                name="weekDay"
                                error={dayEmpty}
                                margin="normal"
                                select
                                className={classes.textField}
                                variant="outlined"
                                label="Day"
                            >
                                <MenuItem value={'MONDAY'}>Monday</MenuItem>
                                <MenuItem value={'TUESDAY'}>Tuesday</MenuItem>
                                <MenuItem value={'WEDNESDAY'}>Wednesday</MenuItem>
                                <MenuItem value={'THURSDAY'}>Thursday</MenuItem>
                                <MenuItem value={'FRIDAY'}>Friday</MenuItem>
                                <MenuItem value={'SATURDAY'}>Saturday</MenuItem>
                                <MenuItem value={'SUNDAY'}>Sunday</MenuItem>
                            </TextField>
                        </div>
                        <div className={classes.innerContainer}>
                            <Button
                                variant="contained"
                                color="primary"
                                className={classes.button}
                                onClick={this.handleClick}
                                style={{ marginTop: 8 }}
                            >
                                Add TimeSlot
                        </Button>
                        </div>
                    </div>
                    <div>
                        <h1 style={{ marginTop: 40 }}>My TimeSlots</h1>
                    </div>
                    <div className={classes.currentContainer}>
                        <Paper className={classes.tableContainer}>
                            <Table>
                                <TableHead>
                                    <TableRow>
                                        <TableCell align="left">Start time</TableCell>
                                        <TableCell align="left">End time</TableCell>
                                        <TableCell align="left">Day of the week</TableCell>
                                        <TableCell align="left"></TableCell>
                                    </TableRow>
                                </TableHead>
                                <TableBody>
                                    {allTimeSlots.map((timeSlot, index) => {
                                        return (
                                            <TableRow>
                                                <TableCell align="left">{timeSlot.startTime}</TableCell>
                                                <TableCell align="left">{timeSlot.endTime}</TableCell>
                                                <TableCell align="left">{timeSlot.dayOfTheWeek}</TableCell>
                                                <TableCell align='left' size='small' >
                                                    <IconButton
                                                        id={index}
                                                        onClick={() => this.handleClickDelete(index)}
                                                    >
                                                        <DeleteIcon />
                                                    </IconButton>
                                                </TableCell>
                                            </TableRow>
                                        )
                                    })}
                                </TableBody>
                            </Table>
                        </Paper>
                    </div>
                    <Snackbar
                        anchorOrigin={{
                            vertical: 'bottom',
                            horizontal: 'center',
                        }}
                        open={open}
                        autoHideDuration={3000}
                        onClose={this.handleClose}
                    >
                        <SnackbarContent
                            message={<span>A booking is associated with this timeslot!</span>}
                            action={[
                                <IconButton
                                    key="close"
                                    aria-label="Close"
                                    color="inherit"
                                    onClick={this.handleClose}
                                >
                                    <CloseIcon />
                                </IconButton>,
                            ]}
                            style={{
                                backgroundColor: '#3f51b5'
                            }}
                        >
                        </SnackbarContent>
                    </Snackbar>
                </div>
            </div>
        )
    }
}

TimeSlotPage.propTypes = {
    classes: PropTypes.object.isRequired,
}

export default withStyles(styles)(TimeSlotPage);
