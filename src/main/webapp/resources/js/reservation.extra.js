$(document).ready(function () {
    var $stateBtn = $("#state-btn");
    var $stateMenuItem = $("a.state-menu-item");
    var reservationId = location.pathname.split("/")[3];    // temporal

    var stateText = ["취소 됨", "검토 중", "답변 완료", "예약 확정"];
    var stateCss = ["btn-default", "btn-info", "btn-primary", "btn-success"];

    var changeResevationState = function (id, state) {
        if (state < 0 || state > 3) {
            return;
        }

        $.ajax({
            type: "POST",
            url: "/api/reservation/" + id + "/state/" + state,
            data: {
                _method: "PUT"
            }
        }).done(function () {
            for (var i = 0; i < stateCss.length; ++i) {
                $stateBtn.removeClass(stateCss[i]);
            }
            $stateBtn.addClass(stateCss[state]);
            $stateBtn.html(stateText[state] + ' <span class="caret"></span>');
        });

    };

    $stateMenuItem.click(function (e) {
        e.preventDefault();
        var newState = $stateMenuItem.index($(this));
        changeResevationState(reservationId, newState);

    });
});