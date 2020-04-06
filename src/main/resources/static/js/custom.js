// alert for submissions
$(document).ready(function () {
    $("#submit-btn").click(function () {
        alert("Thank you for your submission! It will be reviewed by an admin.");
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
// $(document).ready(function () {
//     $("#updateSaved").click(function () {
//         alert("Your account has been updated");
//     });
// });

// confirmation window when "delete user button is pressed"
$(function () {
    $('#deleteBtn').click(function () {
        return window.confirm("Are you sure you want to delete this user?");
    });
});
