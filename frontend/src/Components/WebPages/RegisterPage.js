import React, { Component } from 'react'

// Components
import ButtonAppBar from "../TopBar"
import RegistrationForm from '../Form/RegistrationForm'

export default class RegisterPage extends Component {
    render() {
        return (
            <div>
                <ButtonAppBar />
                <RegistrationForm />
            </div>
        )
    }
}
