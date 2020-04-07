import { Calendar } from '@fullcalendar/core';
import '@fullcalendar/core/main.css';

document.addEventListener('DOMContentLoaded', function() {
    let calendarEl = document.getElementById('calendar');

    let calendar = new Calendar(calendarEl)

calendar.render();
});