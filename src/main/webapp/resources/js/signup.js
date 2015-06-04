$(document).ready(function () {
    var $emailError = $("#email-error");
    var $passwordError = $("#password-error");
    var $pwConfirmError = $("#password-confirm-error");
    var $nameError = $("#displayName-error");
    var $phoneError = $("#phone-error");
    var $signUpForm = $("#signup-form");

    $("#signup-email").focus(function () {
        $emailError.html("");
        $emailError.addClass("hidden");
        $emailError.parent().removeClass("has-error");
    }).blur(function () {
        var $email = $(this).val().replace(/[\s]+/g, "");
        $(this).val($email);
        if (!validator.isVailidEmail($email)) {
            $emailError.html("올바른 이메일 주소가 아닙니다. 다시 확인해주세요!");
            $emailError.removeClass("hidden")
            $emailError.parent().addClass("has-error");
        }
    });

    $("#signup-password").focus(function () {
        $passwordError.addClass("hidden");
        $passwordError.parent().removeClass("has-error");
    }).blur(function () {
        var $password = $(this).val().replace(/[\s]+/g, "");
        $(this).val($password);
        if (!validator.isVailidLength($password, 6, 24)) {
            $passwordError.removeClass("hidden");
            $passwordError.parent().addClass("has-error");
        }
    });

    $("#signup-password-confirm").focus(function () {
        $pwConfirmError.addClass("hidden");
        $pwConfirmError.parent().removeClass("has-error");
    }).blur(function () {
        if ($("#signup-password").val() != $(this).val()) {
            $pwConfirmError.removeClass("hidden");
            $pwConfirmError.parent().addClass("has-error");
        }
    });

    $("#signup-name").focus(function () {
        $nameError.addClass("hidden");
        $nameError.parent().removeClass("has-error");
    }).blur(function () {
        var $name = $(this).val().replace(/[\s]+/g, "");
        $(this).val($name);
        if (!validator.isVailidLength($name, 2, 20)) {
            $nameError.removeClass("hidden");
            $nameError.parent().addClass("has-error");
        }
    });

    $("#signup-phone").focus(function () {
        $phoneError.addClass("hidden");
        $phoneError.parent().removeClass("has-error");
    }).blur(function () {
        var $phone = $(this).val().replace(/[^0-9]+/g, "");
        $(this).val($phone);
        if (!validator.isVailidLength($phone, 10, 11)) {
            $phoneError.removeClass("hidden");
            $phoneError.parent().addClass("has-error");
        }
    });

    $signUpForm.bind("submit", function (e) {
        e.preventDefault();
        if ($signUpForm.find("div.has-error").length != 0)
            return;

        var data = collectFormData($signUpForm.find("input"));
        delete data['passwordConfirm'];

        for (var index in data)
            if (data[index] === "")
                return;

        $.ajax({
            url: "/api/signup",
            type: "POST",
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(data)
        }).done(function (result) {
            if (result.status == "SUCCESS") {
                document.location.href = "/";
                return;
            }
            var error;
            for (var i = 0; i < result.errorMessageList.length; ++i) {
                error = result.errorMessageList[i];
                $("#" + error.fieldName + "-error")
                    .removeClass("hidden")
                    .text(error.message).parent().addClass("has-error");
            }
        });
    });
});