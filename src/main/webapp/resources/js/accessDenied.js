$(document).ready(function () {
    $("#reservation-list > a.access-denied").click(function (e) {
        e.preventDefault();
        $("#r-access-modal").modal('show');
    });
});