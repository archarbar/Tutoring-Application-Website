import axios from 'axios';

var API = {

    // Tutor controller calls

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
    },

    getTutorById(tutorId) {
        const requestUrl = '/tutor/' + tutorId;

        return axios({
            method: 'get',
            url: requestUrl,
        })
    },

    getBookingById(bookingId) {
        const requestUrl = '/booking/' + bookingId;

        return axios({
            method: 'get',
            url: requestUrl,
        })
    },

    getAllBookings() {
        const requestUrl = '/bookings/';

        return axios({
            method: 'get',
            url: requestUrl,
        })
    },

    getBookingByDate(date) {
        const requestUrl = '/booking/date/' + date;

        return axios({
            method: 'get',
            url: requestUrl,
        })
    },

    deleteBooking(bookingId) {
        const requestUrl = '/booking/decline/' + bookingId;

        return axios({
            method: 'delete',
            url: requestUrl,
        })
    },

    changeHourlyRate(tutorForm) {
        const hourlyRate = tutorForm.hourlyRate;
        const tutorId = tutorForm.tutorId;

        const requestUrl = '/tutor/hourlyrate/' + hourlyRate + '?tutorId=' + tutorId; 

        return axios({
            method: 'put',
            url: requestUrl,
            data: tutorForm,
        })
    }


}

export default API;
