import React, { Component } from 'react'

// Components
import LoginForm from '../Form/LoginForm'
import ButtonAppBar from "../TopBar/TopBar"

export default class LoginPage extends Component {
    render() {
        return (
            <div>
                <ButtonAppBar />
                <LoginForm />
            </div>
        )
    }
}
