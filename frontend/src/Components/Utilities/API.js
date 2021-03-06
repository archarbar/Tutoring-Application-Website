import axios from 'axios';

var API = {

    // BOOKING CONTROLLER CALLS

    getAllBookingsByTutor(tutorId) {
        const requestUrl = '/booking/tutor/' + tutorId;

        return axios({
            method: 'get',
            url: requestUrl,
            data: tutorId,
        })
    },

    acceptBooking(bookingId) {
        const requestUrl = '/booking/accept/' + bookingId;

        return axios({
            method: 'delete',
            url: requestUrl,
        })
    },

    declineBooking(bookingId) {
        const requestUrl = '/booking/decline/' + bookingId;

        return axios({
            method: 'delete',
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

    addCourseForTutor(courseForm) {
        const courseName = courseForm.courseName;
        const tutorId = courseForm.tutorId;

        const requestUrl = '/course/new?courseName=' + courseName + '&tutorId=' + tutorId;

        return axios({
            method: 'post',
            url: requestUrl,
            data: courseForm,
        })
    },

    getCourseForTutor(tutorId) {
        const requestUrl = '/course/' + tutorId + '/get';

        return axios({
            method: 'get',
            url: requestUrl,
            data: tutorId,
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
            data: bookingForm,
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
        const stars = ratingForm.stars;
        const comment = ratingForm.comment;
        const studentId = ratingForm.studentId;
        const tutoringSessionId = ratingForm.tutoringSessionId;

        const requestUrl = '/rating/new/?stars=' + stars + '&comment=' + comment + '&studentId=' + studentId + '&tutoringSessionId' + tutoringSessionId;

        return axios({
            method: 'post',
            url: requestUrl,
            data: ratingForm,
        })
    },

    getAllRatings() {
        const requestUrl = '/ratings/';

        return axios({
            method: 'get',
            url: requestUrl,
        })
    },

    getStudentRatingByBooking(bookingId) {
        const requestUrl = '/rating/student/booking/' + bookingId;

        return axios({
            method: 'get',
            url: requestUrl,
        })
    },

    // ROOM CONTROLLER CALLS
    getRoomById(roomId) {
        const requestUrl = '/room/' + roomId;

        return axios({
            method: 'get',
            url: requestUrl,
        })
    },

    // STUDENT CONTROLLER CALLS
    getStudentById(studentId) {
        const requestUrl = '/student/' + studentId;

        return axios({
            method: 'get',
            url: requestUrl,
        })
    },

    getStudentByEmail(studentEmail) {
        const requestUrl = '/student/' + studentEmail;

        return axios({
            method: 'get',
            url: requestUrl,
        })
    },

    getStudentByFirstName(studentFirstName) {
        const requestUrl = '/student/' + studentFirstName;

        return axios({
            method: 'get',
            url: requestUrl,
        })
    },

    getStudentByLastName(studentLastName) {
        const requestUrl = '/student/' + studentLastName;

        return axios({
            method: 'get',
            url: requestUrl,
        })
    },

    // TIME SLOT CONTROLLER CALLS
    getTimeSlotById(timeSlotId) {
        const requestUrl = '/timeslot/' + timeSlotId;

        return axios({
            method: 'get',
            url: requestUrl,
        })
    },

    getTimeSlotByTutor(tutorId) {
        const requestUrl = '/timeslot/tutor/' + tutorId;

        return axios({
            method: 'get',
            url: requestUrl,
            data: tutorId,
        })
    },

    createTimeSlot(timeSlotForm) {
        const startTime = timeSlotForm.startTime;
        const endTime = timeSlotForm.endTime;
        const weekDay = timeSlotForm.weekDay;

        const requestUrl = '/timeslot/new/?startTime=' + startTime + '&endTime=' + endTime + '&weekDay=' + weekDay;

        return axios({
            method: 'post',
            url: requestUrl,
            data: timeSlotForm,
        })
    },


    addTimeSlotForTutor(timeSlotForm) {
        const startTime = timeSlotForm.startTime;
        const endTime = timeSlotForm.endTime;
        const dayOfTheWeek = timeSlotForm.dayOfTheWeek;
        const tutorId = timeSlotForm.tutorId;

        const requestUrl = '/timeslot/tutor/new/?startTime=' + startTime + '&endTime=' + endTime + '&dayOfTheWeek=' + dayOfTheWeek + '&tutorId=' + tutorId;

        return axios({
            method: 'post',
            url: requestUrl,
            data: timeSlotForm,
        })
    },

    removeTimeSlotForTutor(timeSlotForm) {
        const timeSlotId = timeSlotForm.timeSlotId
        const tutorId = timeSlotForm.tutorId;

        const requestUrl = '/timeslot/tutor/delete?timeSlotId=' + timeSlotId + '&tutorId=' + tutorId;

        return axios({
            method: 'delete',
            url: requestUrl,
            data: timeSlotForm,
        })
    },

    getTimeSlotByBooking(bookingId) {
        const requestUrl = '/timeslot/booking' + bookingId;

        return axios({
            method: 'get',
            url: requestUrl,
        })
    },

    getTimeSlotByTutoringSession(tutoringSessionId) {
        const requestUrl = '/timeslot/tutoringsession' + tutoringSessionId;

        return axios({
            method: 'get',
            url: requestUrl,
        })
    },

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

        const requestUrl = '/login/' + email + '/' + password;

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

    // TUTORING SESSION CONTROLLER CALLS
    getTutoringSessionById(tutoringSessionId) {
        const requestUrl = '/tutoringSession/' + tutoringSessionId;

        return axios({
            method: 'get',
            url: requestUrl,
        })
    },

    createTutoringSession(tutoringSessionForm) {
        const bookingId = tutoringSessionForm.bookingId;
        const roomId = tutoringSessionForm.endTiroomIdme;
        const tutorId = tutoringSessionForm.tutorId;

        const requestUrl = '/tutoringSession/new/?bookingId=' + bookingId + '&roomId=' + roomId + '&tutorId=' + tutorId;

        return axios({
            method: 'post',
            url: requestUrl,
            data: tutoringSessionForm,
        })
    },

    getTutoringSessionByTutor(tutorId) {
        const requestUrl = '/tutoringsession/tutor/' + tutorId;

        return axios({
            method: 'get',
            url: requestUrl,
        })
    },

    getAllTutoringSessions() {
        const requestUrl = '/tutoringsessions/';

        return axios({
            method: 'get',
            url: requestUrl,
        })
    },
}

export default API;
