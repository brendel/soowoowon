$(document).ready(function () {
    $.datepicker.setDefaults({
        "prevText": '&#x3c;',
        "nextText": '&#x3e;',
        closeText: '닫기',
        currentText: '오늘',
        monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
        monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
        dayNames: ['일', '월', '화', '수', '목', '금', '토'],
        dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
        dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
        weekHeader: 'Wk',
        dateFormat: 'yy-mm-dd',
        firstDay: 0,
        isRTL: false,
        showMonthAfterYear: true,
        yearSuffix: '년',
        minDate: 1,
        "beforeShow": function (i, o) {
            $(o.dpDiv).addClass('panel panel-default');
        }
    });

    $.datepicker.__generateHTML = $.datepicker._generateHTML;
    //overwrite _generateHTML
    $.datepicker._generateHTML = function (inst) {
        return '<div class="panel-body">'
            + $.datepicker.__generateHTML(inst)
                //adjust the width.
                .replace('<table', '<table style="width:200px;"')
                //replace classes.
                .replace('ui-datepicker-prev', 'pull-left btn btn-default btn-sm')
                .replace('ui-datepicker-next', 'pull-right btn btn-default btn-sm')
                .replace('ui-datepicker-title', 'text-center')
                .replace('ui-state-default ui-state-active', 'btn btn-primary btn-block btn-xs')
                .replace(/ui-state-default ui-priority-secondary/g, 'btn btn-info btn-block btn-xs')
                .replace(/ui-state-default/g, 'btn btn-link btn-block btn-xs')
            + '</div>';
    };
    $("#date-area").click(function () {
        $("#reservation-date").datepicker().focus();
        $("td.ui-state-disabled > span").each(function () {
            $(this).removeClass("btn-link");
            $(this).addClass("disabled");
        });
    });

    var $reservationForm = $("#reservation-form");
    var $inputs = $reservationForm.find("input:text, select, textarea");

    $inputs.focus(function () {
        $(this).parent().removeClass("has-error");
    });

    $reservationForm.bind("submit", function (e) {
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
        var url = "/api/reservation";
        if (window.location.pathname.lastIndexOf("update") != -1) {
            url += '/' + window.location.pathname.split('/')[3];
        }

        $.ajax({
            type: 'POST',
            dataType: 'json',
            contentType: 'application/json',
            url: url,
            data: JSON.stringify(data)
        }).done(function (result) {
            if (result.status == "SUCCESS") {
                document.location.href = "/reservation";
                return;
            }
            var error;
            for (var i = 0; i < result.errorMessageList.length; ++i) {
                error = result.errorMessageList[i];
                $("#reservation-" + error.fieldName)
                    .parent().addClass("has-error");
            }
        });
    });
});
