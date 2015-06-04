$(document).ready(function () {
    if (getQueryVariable("passwordChanged")) {
        $("#password-changed").removeClass("hidden");
    }
    if (getQueryVariable("userinfoEdited")) {
        $("#userinfo-edited").removeClass("hidden");
    }
    var $nameError = $("#displayName-error");
    var $phoneError = $("#phone-error");
    var $editUserForm = $("#edit-user-form");

    var showAlert = function (message) {
        $("#alert-area")
            .html('<div class="alert alert-success alert-dismissible" role="alert"><button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button><strong>완료!</strong> ' + message + '</div>');
        $("#userinfo-tabs a:first").tab("show");
    };

    $("#edit-user-name").focus(function () {
        $nameError.addClass("hidden");
        $nameError.parent().removeClass("has-error");
    }).blur(function () {
        var name = $(this).val().replace(/[\s]+/g, "");
        $(this).val(name);
        if (!validator.isVailidLength(name, 2, 20)) {
            $nameError.removeClass("hidden");
            $nameError.parent().addClass("has-error");
        }
    });

    $("#edit-user-phone").focus(function () {
        $phoneError.addClass("hidden");
        $phoneError.parent().removeClass("has-error");
    }).blur(function () {
        var phone = $(this).val().replace(/[^0-9]+/g, "");
        $(this).val(phone);
        if (!validator.isVailidLength(phone, 10, 11)) {
            $phoneError.removeClass("hidden");
            $phoneError.parent().addClass("has-error");
        }
    });

    $editUserForm.bind("submit", function (e) {
        e.preventDefault();
        if ($editUserForm.find("div.has-error").length != 0)
            return;

        var data = collectFormData($editUserForm.find("input"));

        for (var index in data)
            if (data[index] === "")
                return;

        $.ajax({
            url: "/api/userinfo/edit",
            type: "POST",
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(data)
        }).done(function (result) {
            if (result.status == 'SUCCESS') {
                $("#profile-name").html(data['displayName']);
                $("#profile-phone").html(data['phone']);
                showAlert('회원정보가 성공적으로 수정되었습니다.')
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

    var $oldPasswordError = $("#oldPassword-error");
    var $newPasswordError = $("#newPassword-error");
    var $pwConfirmError = $("#newPassword-confirm-error");
    var $editPasswordForm = $("#edit-password-form");

    $("#old-password").focus(function () {
        $oldPasswordError.addClass("hidden");
        $oldPasswordError.parent().removeClass("has-error");
    }).blur(function () {
        var $password = $(this).val().replace(/[\s]+/g, "");
        $(this).val($password);
        if (!validator.isVailidLength($password, 6, 24)) {
            $oldPasswordError.removeClass("hidden");
            $oldPasswordError.parent().addClass("has-error");
        }
    });

    $("#new-password").focus(function () {
        $newPasswordError.addClass("hidden");
        $newPasswordError.parent().removeClass("has-error");
    }).blur(function () {
        var $password = $(this).val().replace(/[\s]+/g, "");
        $(this).val($password);
        if (!validator.isVailidLength($password, 6, 24)) {
            $newPasswordError.removeClass("hidden");
            $newPasswordError.parent().addClass("has-error");
        }
    });

    $("#new-password-confirm").focus(function () {
        $pwConfirmError.addClass("hidden");
        $pwConfirmError.parent().removeClass("has-error");
    }).blur(function () {
        if ($("#new-password").val() != $(this).val()) {
            $pwConfirmError.removeClass("hidden");
            $pwConfirmError.parent().addClass("has-error");
        }
    });


    $editPasswordForm.bind("submit", function (e) {
        e.preventDefault();
        if ($editPasswordForm.find("div.has-error").length != 0)
            return;

        var data = collectFormData($editPasswordForm.find("input"));
        delete data['newPasswordConfirm'];

        for (var index in data)
            if (data[index] === "")
                return;

        $.ajax({
            url: "/api/userinfo/password",
            type: "POST",
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(data)
        }).done(function (result) {
            if (result.status == "SUCCESS") {
                showAlert('비밀번호가 성공적으로 변경되었습니다.');
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