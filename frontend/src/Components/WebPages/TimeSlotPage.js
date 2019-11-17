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
        borderRadius: 40,
        padding: 10
    },
    currentContainer: {
        border: '1px solid #000000',
        minHeight: 400,
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'center',
        marginTop: 20,
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
            startEmpty: false,
            endEmpty: false,
            startError: false,
            endError: false
        }
        this.handleClick = this.handleClick.bind(this);
    }

    componentDidMount() {
        document.title = "TutorGang | My TimeSlots"
    }

    handleEvent = e => {
        this.setState({ [e.target.name]: e.target.value })
    }

    handleClick() {
        const { startTime, endTime, weekDay } = this.state;
        var startEmpty = this.state.startTime === '';
        var endEmpty = this.state.endTime === '';

        if (startEmpty || endEmpty) {
            this.setState({
                startEmpty: true,
                endEmpty: true,
            })
        } else {
            var noErrorStart = ((parseInt(this.state.startTime.slice(-2) % 30)) === 0 && parseInt(this.state.startTime.slice(0, 2)) >= 9 && parseInt(this.state.startTime.slice(0, 2)) <= 22);
            var noErrorEnd = ((parseInt(this.state.endTime.slice(-2) % 30)) === 0 && parseInt(this.state.endTime.slice(0, 2)) >= 9 && parseInt(this.state.endTime.slice(0, 2)) <= 22);
            if (noErrorStart && noErrorEnd) {
                API.createTimeSlot({ 'startTime': startTime, 'endTime': endTime, 'weekDay': 'Monday' })
            }
            else {
                this.setState({
                    startError: noErrorStart,
                    endError: noErrorEnd,
                })
            }
        }
    }

    render() {

        const { classes } = this.props;
        const { endError, endEmpty, startError, startEmpty } = this.state;

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
                            {startError ? <p className={classes.error}>Wrong format!</p> : null}
                            <TextField
                                id="outlined-basic"
                                className={classes.textField}
                                label="Start time"
                                margin="normal"
                                variant="outlined"
                                name="startTime"
                                error={startError || startEmpty}
                                value={this.state.startTime}
                                onChange={e => this.handleEvent(e)}
                                InputProps={{
                                    inputComponent: TextMaskCustom,
                                }}
                            />
                        </div>
                        <div className={classes.innerContainer}>
                            {endEmpty ? <p className={classes.error}>The end time cannot be empty!</p> : null}
                            {endError ? <p className={classes.error}>Wrong format!</p> : null}
                            <TextField
                                id="outlined-basic"
                                className={classes.textField}
                                label="End time"
                                margin="normal"
                                variant="outlined"
                                name="endTime"
                                error={endError || endEmpty}
                                value={this.state.endTime}
                                onChange={e => this.handleEvent(e)}
                                InputProps={{
                                    inputComponent: TextMaskCustom,
                                }}
                            />
                        </div>
                        <div className={classes.innerContainer}>
                            <FormControl variant="outlined" className={classes.textField} style={{marginTop: 8}}>
                                <InputLabel>
                                    Day
                                </InputLabel>
                                <Select
                                    labelWidth={25}
                                >
                                    <MenuItem value={'Monday'}>Monday</MenuItem>
                                    <MenuItem value={'Tuesday'}>Tuesday</MenuItem>
                                    <MenuItem value={'Wednesday'}>Wednesday</MenuItem>
                                    <MenuItem value={'Thursday'}>Thursday</MenuItem>
                                    <MenuItem value={'Friday'}>Friday</MenuItem>
                                    <MenuItem value={'Saturday'}>Saturday</MenuItem>
                                    <MenuItem value={'Sunday'}>Sunday</MenuItem>
                                </Select>
                            </FormControl>
                        </div>
                        <Button
                            variant="contained"
                            color="primary"
                            className={classes.button}
                            onClick={this.handleClick}
                            style={{marginTop: 8}}
                        >
                            Add TimeSlot
                        </Button>
                    </div>
                    <div>
                        <h1 style={{ marginTop: 40 }}>My TimeSlots</h1>
                    </div>
                    <div className={classes.currentContainer}>
                        <h1>LIST TIMESLOTS HERE</h1>
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
