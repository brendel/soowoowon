$(document).ready(function () {
    var $comments = $("#comments");
    var $commentForm = $("#comment-form");

    var $textarea = $("#comment-textarea");

    $commentForm.bind("submit", function (e) {
        e.preventDefault();
        var articleId = location.pathname.split("/")[3];
        var comment = $textarea.val().replace(/[\s]+/g, "");
        $textarea.val(comment);
        if (comment === "") {
            $textarea.parent().addClass("has-error");
            return;
        }


        $.ajax({
            type: "POST",
            url: "/api/" + window.location.pathname.split('/')[1] + "/" + articleId,
            data: {
                content: comment
            }
        }).done(function (response) {
            $textarea.val("");
            var $newComment = $(response);
            $comments.append($newComment);
            $newComment.animate({opacity: 1});
        });
    });


    $comments.on("click", "a.del-comment", function (e) {
        e.preventDefault();
        var url = this.href;
        var $liComment = $(this).parent().parent().parent();
        $.ajax({
            url: url,
            type: "POST",
            data: {
                _method: "DELETE"
            }
        }).done(function () {
            $liComment.css("background-color", "#f2dede")
                .animate({opacity: 0}, function () {
                    $liComment.remove();
                });
        });
    });

    $('#disabled-comment').click(function (e) {
        e.preventDefault();
        $('#login-modal').modal('show');
    });
});