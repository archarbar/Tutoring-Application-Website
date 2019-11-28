// React
import React, { Component } from 'react';
import PropTypes from 'prop-types';

// MUI
import { withStyles } from '@material-ui/core/styles';
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
import Snackbar from '@material-ui/core/Snackbar';
import CloseIcon from '@material-ui/icons/Close';
import SnackbarContent from '@material-ui/core/SnackbarContent';

import SideBar from '../TopBar/SideBar';
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

class CoursesPage extends Component {

    constructor(props) {
        super(props);
        this.state = {
            courseName: '',
            courseLevel: '',
            nameEmpty: false,
            levelEmpty: false,
            tutorId: '',
            open: false,
            allCourses: [],
        }
        this.handleClick = this.handleClick.bind(this);
        this.getAllCourses();
    }

    componentDidMount() {
        document.title = "BigBrain Tutoring | MyCourses";
        this.getAllCourses();
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
            this.setState({
                open: true,
                courseName: '',
                courseLevel: '',
            })
        }
    }

    handleClose = () => {
        this.setState({
            open: false,
        });
    };

    getAllCourses() {
        var allData;
        API.getCourseForTutor(localStorage.getItem('tutorId')).then(res => {
            console.log(JSON.stringify(res));
            allData = res.data.map(x => {
                return x;
            });
            this.setState({
                allCourses: allData,
            });
        });
        return allData;
    }

    render() {

        const { classes } = this.props;
        const { nameEmpty, levelEmpty, open, allCourses } = this.state;

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
                            <TextField
                                labelWidth={40}
                                onChange={e => this.handleEvent(e)}
                                value={this.state.courseLevel}
                                name="courseLevel"
                                error={levelEmpty}
                                margin="normal"
                                select
                                className={classes.textField}
                                variant="outlined"
                                label="Level"
                            >
                                <MenuItem value={'HIGHSCHOOL'}>Highschool</MenuItem>
                                <MenuItem value={'CEGEP'}>CEGEP</MenuItem>
                                <MenuItem value={'UNIVERSITY'}>University</MenuItem>
                            </TextField>
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
                                    {allCourses.map(course => {
                                        return (
                                            <TableRow>
                                                <TableCell align="left">{course.courseName}</TableCell>
                                                <TableCell align="left">{course.level}</TableCell>
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
                            message={<span>Course request sent to the manager!</span>}
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

CoursesPage.propTypes = {
    classes: PropTypes.object.isRequired,
}

export default withStyles(styles)(CoursesPage);
