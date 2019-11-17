import React, { Component } from 'react'

// Components
import LoginForm from '../Form/LoginForm'
import ButtonAppBar from "../TopBar/TopBar"

export default class LoginPage extends Component {
    // constructor(props){
    //     super(props);
    // }
    render() {
        return (
            <div>
                <ButtonAppBar />
                <LoginForm setId={this.props.setId} />
            </div>
        )
    }
}
