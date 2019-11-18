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
        width: '50%',
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
        width: '50%',
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

function createData(courseName, courseLevel) {
    return { courseName, courseLevel };
}

const rows = [
    createData('ECSE321', 'UNIVERSITY'),
    createData('ECSE211', 'UNIVERSITY'),
    createData('COMP206', 'UNIVERSITY'),
];

class CoursesPage extends Component {

    constructor(props) {
        super(props);
        this.state = {
            courseName: '',
            courseLevel: '',
            nameEmpty: false,
            levelEmpty: false,
            tutorId : '',
        }
        this.handleClick = this.handleClick.bind(this);
    }

    componentDidMount() {
        document.title = "BigBrain Tutoring | MyCourses";
    }

    handleEvent = e => {
        this.setState({ [e.target.name]: e.target.value })
    }

    handleClick() {
        const { courseName, courseLevel } = this.state;
        var nameEmpty = courseName === '';
        var levelEmpty = courseLevel === '';
        var id = localStorage.getItem('tutorId');

        if (nameEmpty || levelEmpty) {
            this.setState({
                nameEmpty: true,
                levelEmpty: true,
            })
        } else {
            API.addCourseForTutor({ 'courseName': courseName, 'tutorId': id });
        }
    }

    render() {

        const { classes } = this.props;
        const { nameEmpty, levelEmpty } = this.state;

        return (
            <div>
                <SideBar />
                <div className={classes.mainContainer}>
                    <div>
                        <h1 style={{ marginTop: 0 }}>Add New Course</h1>
                    </div>
                    <div className={classes.newContainer}>
                        <div className={classes.innerContainer}>
                            {nameEmpty ? <p className={classes.error}>The course name cannot be empty!</p> : null}
                            <TextField
                                id="outlined-basic"
                                className={classes.textField}
                                label="Course name"
                                margin="normal"
                                variant="outlined"
                                name="courseName"
                                value={this.state.courseName}
                                error={nameEmpty}
                                onChange={e => this.handleEvent(e)}
                            />
                        </div>
                        <div className={classes.innerContainer}>
                            {levelEmpty ? <p className={classes.error}>The course level cannot be empty!</p> : null}
                            <FormControl variant="outlined" className={classes.textField} style={{ marginTop: 8 }}>
                                <InputLabel>
                                    Level
                                </InputLabel>
                                <Select
                                    labelWidth={40}
                                    onChange={e => this.handleEvent(e)}
                                    value={this.state.courseLevel}
                                    name="courseLevel"
                                    error={levelEmpty}
                                >
                                    <MenuItem value={'HIGHSCHOOL'}>Highschool</MenuItem>
                                    <MenuItem value={'CEGEP'}>CEGEP</MenuItem>
                                    <MenuItem value={'UNIVERSITY'}>University</MenuItem>
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
                            Add Course
                        </Button>
                    </div>
                    <div>
                        <h1 style={{ marginTop: 40 }}>My Courses</h1>
                    </div>
                    <div className={classes.currentContainer}>
                        <Paper className={classes.tableContainer}>
                            <Table>
                                <TableHead>
                                    <TableRow>
                                        <TableCell align="left">Course name</TableCell>
                                        <TableCell align="left">Level</TableCell>
                                    </TableRow>
                                </TableHead>
                                <TableBody>
                                    {rows.map(row => (
                                        <TableRow key={row.name}>
                                            <TableCell align="left">{row.courseName}</TableCell>
                                            <TableCell align="left">{row.courseLevel}</TableCell>
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

CoursesPage.propTypes = {
    classes: PropTypes.object.isRequired,
}

export default withStyles(styles)(CoursesPage);
