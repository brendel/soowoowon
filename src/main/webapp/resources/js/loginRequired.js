$(document).ready(function () {
    $("#reservation-list > a.list-group-item").click(function (e) {
        if (isLoggedIn) {
            e.originalEvent();
            return;
        }
        else {
            e.preventDefault();
            $("#login").click();
            $("#reservation-callout").removeClass("hidden");
            return;
        }
    });
});