import axios from 'axios';

var API = {
    registerTutor(registerForm) {
        const firstName = registerForm.firstName;
        const lastName = registerForm.lastName;
        const email = registerForm.email;
        const password = registerForm.password;

        const requestUrl = '/tutor/new?tutorFirstName=' + firstName + '&tutorLastName=' + lastName + '&tutorEmail=' + email + '&tutorPassword=' + password;

        return axios({
           method: 'post',
           url: requestUrl,
           data: registerForm,
        });
    },

    loginTutor(loginForm) {
        const email = loginForm.email;
        const password = loginForm.password;

        const requestUrl = '/login?Email=' + email + '&Password=' + password;

        return axios({
            method: 'get',
            url: requestUrl,
            data: loginForm,
        })
    }
}

export default API;
