import axios from 'axios';

var API = {

    // TUTOR CONTROLLER CALLS
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

    changeHourlyRate(tutorForm) {
        const hourlyRate = tutorForm.hourlyRate;
        const tutorId = tutorForm.tutorId;

        const requestUrl = '/tutor/hourlyrate/' + hourlyRate + '?tutorId=' + tutorId; 

        return axios({
            method: 'put',
            url: requestUrl,
            data: tutorForm,
        })
    },

    // BOOKING CONTROLLER CALLS
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

    // COURSE CONTROLLER CALLS
    getCourseById(courseId) {
        const requestUrl = '/course/' + courseId;

        return axios({
            method: 'get',
            url: requestUrl,
        })
    },

    addCourseForTutor(tutorId) {
        const requestUrl = '/course/' + tutorId + '/add';

        return axios({
            method: 'post',
            url: requestUrl,
        })
    },

    getCoursesByLevel(level) {
        const requestUrl = '/course/level/' + level;

        return axios({
            method: 'get',
            url: requestUrl,
        })
    },

    getCourseByName(courseName) {
        const requestUrl = '/course/name/' + courseName;

        return axios({
            method: 'get',
            url: requestUrl,
        })
    },

    getAllCourses() {
        const requestUrl = '/courses/';

        return axios({
            method: 'get',
            url: requestUrl,
        })
    },

    // MANAGER CONTROLLER CALLS
    getManagerById(managerId) {
        const requestUrl = '/manager/' + managerId;

        return axios({
            method: 'get',
            url: requestUrl,
        })
    },

    getAllManagers() {
        const requestUrl = '/managers/';

        return axios({
            method: 'get',
            url: requestUrl,
        })
    },

    // NOTIFICATION CONTROLLER CALLS
    getNotificationById(notificationId) {
        const requestUrl = '/notification/' + notificationId;

        return axios({
            method: 'get',
            url: requestUrl,
        })
    },

    getNotificationByTutor(tutorId) {
        const requestUrl = '/notification/tutor/' + tutorId;

        return axios({
            method: 'get',
            url: requestUrl,
        })
    },

    createNotification(bookingForm) {
        const tutorId = bookingForm.tutorId;
        const bookingId = bookingForm.bookingId;

        const requestUrl = '/notification/new/?tutorId=' + tutorId + '&bookingId=' + bookingId;

        return axios({
            method: 'post',
            url: requestUrl,
        })
    },

    // RATING CONTROLLER CALLS
    getRatingById(ratingId) {
        const requestUrl = '/rating/' + ratingId;

        return axios({
            method: 'get',
            url: requestUrl,
        })
    },

    createRating(ratingForm) {
        const stars = bookingForm.stars;
        const comment = bookingForm.comment;
        const studentId = bookingForm.studentId;
        const tutoringSessionId = bookingForm.tutoringSessionId;

        const requestUrl = '/rating/new/?stars=' + stars + '&comment=' + comment + '&studentId=' + studentId + '&tutoringSessionId' + tutoringSessionId;

        return axios({
            method: 'post',
            url: requestUrl,
        })
    },

    getAllRatings() {
        const requestUrl = '/ratings/';

        return axios({
            method: 'get',
            url: requestUrl,
        })
    },

}

export default API;
