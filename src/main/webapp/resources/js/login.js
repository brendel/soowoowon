$(document).ready(function () {
    var $loginForm = $("#login-form");

    $("#login-modal").on("hide.bs.modal", function () {
        $(this).find("div.login-modal-callout").addClass("hidden");
    });

    $loginForm.bind("submit", function (e) {
        e.preventDefault();
        $inputs = $loginForm.find("input");
        var data = collectFormData($inputs);
        if ($inputs.find('input:checkbox:checked')) {
            data.remember_me = 1;
        } else {
            data.remember_me = 0;
        }


        $.ajaxSetup({
            statusCode: {
                401: function () {
                    $("#login-alert").removeClass("hidden");
                }
            }
        });

        $.ajax({
            url: "/login",
            type: "POST",
            data: data
        }).done(function (response) {
            $("#login-alert").addClass("hidden");
            document.location.href = response.redirect;
        });
    });
});