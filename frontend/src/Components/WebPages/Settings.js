// React
import React, { Component } from 'react';
import PropTypes from 'prop-types';

// Material-UI
import { withStyles } from '@material-ui/core/styles';
import Typography from '@material-ui/core/Typography';
import ExpansionPanel from '@material-ui/core/ExpansionPanel';
import ExpansionPanelSummary from '@material-ui/core/ExpansionPanelSummary';

import SideBar from '../TopBar/SideBar'
import API from '../Utilities/API';

const styles = theme => ({
    mainContainer: {
        minWidth: 'calc(100% - 94px)',
        minHeight: '100vh',
        marginRight: 'auto',
        paddingTop: '65px',
    },
    subTitle: {
        width: '100%',
        textAlign: 'center',
        marginTop: '5vh',
        marginBottom: '4vh',
    },
    heading: {
        fontSize: theme.typography.pxToRem(15),
        flexBasis: '22vw',
        flexShrink: 0,
    },
    panel: {
        marginTop: '1%',
        width: '80%',
        marginLeft: 'auto',
        marginRight: 'auto',
        borderStyle: 'solid',
        borderColor: "#DADADA",
        borderWidth: 2,
        borderRadius: 5,
        boxShadow: 'none',
    },
    textField: {
        marginTop: 20,
        marginRight: 'auto',
        marginLeft: 'auto',
        width: '30vw',
        display: 'block'
    },
    button: {
        width: '16.5vw',
        marginTop: '3vh',
        display: 'inline-block'
    },
    name: {
        display: 'inline',
        height: '50px',
        lineHeight: '50px',
        marginLeft: '2.25%',
        color: 'black',
        textAlign: 'left',
        paddingRight: '28%',
        fontSize: 15,
    },
    email: {
        display: 'inline',
        height: '50px',
        lineHeight: '50px',
        marginLeft: '2.25%',
        color: 'black',
        textAlign: 'left',
        paddingRight: '28.5%',
        fontSize: 15,
    },
})

class SettingsPage extends Component {
    constructor(props) {
        super(props)
        this.state = {
            firstName: '',
            lastName: '',
            email: '',
            hourlyRate: ''
        }
    }

    componentDidMount() {
        document.title = "BigBrain Tutoring | Settings"
        var id = localStorage.getItem('tutorId')
        API.getTutorById(id)
        .then(res =>{
            this.setState({
                firstName : res.data.firstName,
                lastName: res.data.lastName,
                email: res.data.email,
                hourlyRate: res.data.hourlyRate
            })
        })
    }

    render() {

        const { classes } = this.props;

        return (
            <div>
                <SideBar />
                <div
                    className={classes.mainContainer}
                >
                    <Typography variant="h6" color="inherit" className={classes.subTitle}>
                        User information
                    </Typography>

                    <div className={classes.panel}>

                        <ExpansionPanel expanded={false}>
                            <ExpansionPanelSummary>
                                <Typography className={classes.heading}>
                                    Name
                                </Typography>
                                <Typography className={classes.secondaryHeading}>
                                    {this.state.firstName} {this.state.lastName}
                                </Typography>
                            </ExpansionPanelSummary>
                        </ExpansionPanel>

                        <ExpansionPanel expanded={false}>
                            <ExpansionPanelSummary>
                                <Typography className={classes.heading}>
                                    Email
                                </Typography>
                                <Typography className={classes.secondaryHeading}>
                                    {this.state.email}
                                </Typography>
                            </ExpansionPanelSummary>
                        </ExpansionPanel>

                        <ExpansionPanel expanded={false}>
                            <ExpansionPanelSummary>
                                <Typography className={classes.heading}>
                                    Hourly Rate
                                </Typography>
                                <Typography className={classes.secondaryHeading}>
                                    {this.state.hourlyRate}
                                </Typography>
                            </ExpansionPanelSummary>
                        </ExpansionPanel>
                    </div>
                </div>
            </div>
        )
    }
}



SettingsPage.propTypes = {
    closeDrawer: PropTypes.func.isRequired,
};

export default withStyles(styles)(SettingsPage);
