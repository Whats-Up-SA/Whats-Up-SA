import { Calendar } from '@fullcalendar/core';
import '@fullcalendar/core/main.css';

document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');

    var calendar = new Calendar(calendarEl)

calendar.render();
});