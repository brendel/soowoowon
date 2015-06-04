var hideSummitBtn = function () {
    $("#article-submit").addClass("hidden");
    $("#article-disabled").removeClass("hidden");
};

var showSummitBtn = function () {
    $("#article-submit").removeClass("hidden");
    $("#article-disabled").addClass("hidden");
};

$(document).ready(function () {

    $("#fileupload").fileupload(
        {
            url: "/api/photo",
            sequentialUploads: true,
            singleFileUploads: false,
            acceptFileTypes: /(\.|\/)(jpe?g|png)$/i,
            maxFileSize: 1024 * 1024 * 10,
            maxNumberOfFiles: 30,
            autoUpload: true,
            previewCrop: true,
            previewMinWidth: 80,
            previewMinHeight: 80
        }
    )
        .bind("fileuuploadedPhotosploadstarted", hideSummitBtn)
        .bind("fileuploadstopped", showSummitBtn)
        .bind("fileuploaddestroy", hideSummitBtn)
        .bind("fileuploaddestroyed", showSummitBtn);

    var $articleForm = $("#article-form");
    var $inputs = $articleForm.find("input:text, textarea");

    $inputs.focus(function () {
        $(this).parent().removeClass("has-error");
    });

    $("#article-submit").click(function (e) {
        e.preventDefault();

        var flag = false;
        var $this;
        $.each($inputs, function () {
            $this = $(this);
            $this.val($.trim($this.val()));
            if ($this.val() === "") {
                $this.parent().addClass("has-error");
                flag = true;
            }
        });
        if (flag) return;

        var data = collectFormData($inputs);

        var deleteBtn = $("#uploaded-photos button.btn-danger");
        data.uploadedPhotos = new Array();
        for (var i = 0; i < deleteBtn.length; ++i) {
            data.uploadedPhotos.push(deleteBtn[i].getAttribute("data-photoid"));
        }

        var url = "/api/article";
        if (window.location.pathname.lastIndexOf("update") != -1) {
            url += '/' + window.location.pathname.split('/')[3];
        }

        $.ajax({
            url: url,
            type: 'POST',
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(data)
        }).done(function (response) {
            if (response.status == "SUCCESS") {
                document.location.href = "/article";
                return;
            }
            var error;
            for (var i = 0; i < response.errorMessageList.length; ++i) {
                error = response.errorMessageList[i];
                $("#article-" + error.fieldName)
                    .parent().addClass("has-error");
            }
        });
    });
});