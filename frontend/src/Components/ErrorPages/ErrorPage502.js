// React
import React from 'react';
import PropTypes from 'prop-types';

// Components
import TopBar from '../TopBar/TopBar';
import Button from '@material-ui/core/Button';

// Material-UI
import { withStyles } from '@material-ui/core/styles';

const styles = () => ({
    mainContainer: {
        display: 'grid',
        gridTemplateRows: 'auto auto auto',
        gridTemplateColumns: '100%',
        minHeight: '100vh',
        textAlign: 'center',
    },
    mainContainerMobile: {
        display: 'grid',
        gridTemplateRows: 'auto auto 25vh',
        gridTemplateColumns: '100%',
        minHeight: '100vh',
        backgroundColor: '#F4F4F4',
        textAlign: 'center',
    },
})

class ErrorPage502 extends React.Component {

    constructor(props) {
        super(props);
        this.handleClick = this.handleClick.bind(this);
    }

    handleClick() {
        this.props.history.goBack()
    }

    render() {

        const { classes } = this.props;

        return (
            <div
                className={classes.mainContainer}
            >
                <TopBar style={{ gridRowStart: 1 }} position='relative' />
                <div
                    style={{
                        gridRowStart: 2,
                        padding: '0 20vw',
                        display: 'flex',
                        flexDirection: 'column',
                        alignItems: 'center',
                    }}
                >
                    <h2>
                        The page could not be reached
                    </h2>
                    <h4>
                        Please check your connection and try again
                    </h4>
                    <Button
                        variant="contained"
                        color="primary"
                        style={{ marginTop: '5vh' }}
                        onClick={this.handleClick}
                    >
                        Reload
                    </Button>
                </div>
            </div>
        )
    }
}

ErrorPage502.propTypes = {
    classes: PropTypes.object.isRequired,
};

export default (withStyles(styles)(ErrorPage502));
