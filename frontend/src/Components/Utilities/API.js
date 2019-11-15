import axios from 'axios';

var API = {
    registerTutor(registerForm) {
        return axios({
           method: 'post',
           url: 'join',
           data: registerForm, 
        });
    }
}

export default API;
