// alert for submissions
$(document).ready(function () {
    $("#submit-btn").click(function () {
        alert("Thank you for your submission. It will be reviewed by an admin.");
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