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
    newContainer: {
        minHeight: 100,
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
    }
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
        }
        this.handleClick = this.handleClick.bind(this);
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

    render() {

        const { classes } = this.props;
        const { endError, endEmpty, startError, startEmpty, dayEmpty, allTimeSlots } = this.state;

        return (
            <div>
                <SideBar />
                <div className={classes.mainContainer}>
                    <div>
                        <h1 style={{ marginTop: 0 }}>Add New TimeSlot</h1>
                    </div>
                    <div className={classes.newContainer}>
                        <div>
                            {startEmpty ? <p className={classes.error}>The start time cannot be empty!</p> : null}
                            {!startError ? <p className={classes.error}>Wrong format!</p> : null}
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
                        <div>
                            {endEmpty ? <p className={classes.error}>The end time cannot be empty!</p> : null}
                            {!endError ? <p className={classes.error}>Wrong format!</p> : null}
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
                        <div>
                            {dayEmpty ? <p className={classes.error}>The week day cannot be empty!</p> : null}
                            <FormControl variant="outlined" className={classes.textField} style={{ marginTop: 8 }}>
                                <InputLabel>
                                    Day
                                </InputLabel>
                                <Select
                                    labelWidth={25}
                                    onChange={e => this.handleEvent(e)}
                                    value={this.state.weekDay}
                                    name="weekDay"
                                    error={dayEmpty}
                                >
                                    <MenuItem value={'MONDAY'}>Monday</MenuItem>
                                    <MenuItem value={'TUESDAY'}>Tuesday</MenuItem>
                                    <MenuItem value={'WEDNESDAY'}>Wednesday</MenuItem>
                                    <MenuItem value={'THURSDAY'}>Thursday</MenuItem>
                                    <MenuItem value={'FRIDAY'}>Friday</MenuItem>
                                    <MenuItem value={'SATURDAY'}>Saturday</MenuItem>
                                    <MenuItem value={'SUNDAY'}>Sunday</MenuItem>
                                </Select>
                            </FormControl>
                        </div>
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
                                    </TableRow>
                                </TableHead>
                                <TableBody>
                                    {allTimeSlots.map(timeSlot => (
                                        <TableRow>
                                            <TableCell align="left">{timeSlot.startTime}</TableCell>
                                            <TableCell align="left">{timeSlot.endTime}</TableCell>
                                            <TableCell align="left">{timeSlot.dayOfTheWeek}</TableCell>
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

TimeSlotPage.propTypes = {
    classes: PropTypes.object.isRequired,
}

export default withStyles(styles)(TimeSlotPage);
