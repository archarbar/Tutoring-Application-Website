import React from 'react';
import { makeStyles } from '@material-ui/core/styles'


const useStyles = makeStyles(theme => ({
    
    main:{
        backgroundColor:'white',
        // width:'100%',
        display: 'inline-block',
        margin:'10px',
        border:'1px solid black',
        padding:'10px'
    },
    title:{
        fontSize: '20',
        margin:'5px',
        // marginLeft:'auto',
        // marginRight:'auto',
        textAlign:'center'
    }

}))



export default function Courses(props){
    const classes = useStyles();

    return(
        <div className={classes.main}>
            <p className = {classes.title}>Name: {props.name}</p>
            <p className = {classes.title}>Level: {props.level}</p>
            {/* <p>ASdlsajkdsad {props.name}</p> */}
        </div>
    );
}