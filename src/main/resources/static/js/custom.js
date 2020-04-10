// alert for submissions
$(function () {
    $("#submit-btn").click(function () {
        if ($('#title').val().length === 0) {
            alert('Please provide an event title.');
            return false;
        }
        if ($('#description').val().length === 0) {
            alert('Please provide an event description.');
            return false;
        }
        if ($('#parentCategory').val().length === 0) {
            alert('Please select a category.');
            return false;
        }
        if ($('#startTime').val().length === 0) {
            alert('Please provide an event start time.');
            return false;
        }
        if ($('#startDate').val().length === 0) {
            alert('Please provide an event start date.');
            return false;
        } else {
            alert('Thank you for your submission! It will be reviewed by an admin.');
            return true;
        }
    });
});

// password confirmation on registration and update page
$(function () {
    $(".btnSubmit").click(function () {
        let password = $(".password").val();
        let confirmPassword = $(".confirmPassword").val();
        if (password !== confirmPassword) {
            alert("Passwords do not match.");
            return false;
        }
        return true;
    });
});

// email confirmation on registration and update page
$(function () {
    $(".btnSubmit").click(function () {
        let email = $(".email").val();
        if ($(email).find(':not(:contains("@"))')) {
            alert("Please enter a valid email address.");
            return false;
        }
        return true;
    });
});

// alert for account updates
$(document).ready(function () {
    $("#updateSaved").click(function () {
        alert("Your account has been updated");
    });
});

// confirmation window when "delete user button is pressed"
$(function () {
    $('#deleteBtn').click(function () {
        return window.confirm("Are you sure you want to delete this user?");
    });
});

// hides broken image
// $(function () {
//     let allImages = document.images;
//     for (let i = 0; i < allImages.length; i++) {
//         allImages[i].onerror = function () {
//             this.style.visibility = "hidden";
//         }
//     }
// })();

// start date datepicker
$(document).ready(function () {
    $("#startDateFull").datepicker({
        altFormat: 'yy-mm-dd',
        altField: ".alternate1",
        dateFormat: "DD, d MM, yy",
        changeMonth: true,
        changeYear: true,
        minDate: 0
    });
});

// end date datepicker
$(document).ready(function () {
    $("#endDateFull").datepicker({
        altFormat: 'yy-mm-dd',
        altField: ".alternate2",
        dateFormat: "DD, d MM, yy",
        changeMonth: true,
        changeYear: true,
        minDate: 0
    });
});
