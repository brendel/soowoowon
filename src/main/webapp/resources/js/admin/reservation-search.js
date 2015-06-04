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
                .replace(/ui-state-default/g, 'btn btn-link btn-block btn-xs')
            + '</div>';
    };

    $("#reservation-from").datepicker({
        onClose: function (selectedDate) {
            $("#reservation-to").datepicker("option", "minDate", selectedDate);
        }
    });
    $("#reservation-to").datepicker({
        onClose: function (selectedDate) {
            $("#reservation-from").datepicker("option", "maxDate", selectedDate);
        }
    });

    $("#reservation-search-submit").click(function (e) {
        e.preventDefault();
        var from = $("#reservation-from").val();
        var to = $("#reservation-to").val();
        var state = $("#reservation-state").val();

        if (from == '') {
            from = '2000-01-01';
        }
        if (to == '') {
            to = '2020-12-12';
        }

        var query = {
            'from': from,
            'to': to,
            'state': state
        };

        $.ajax({
            type: 'POST',
            url: '/admin/reservation/search',
            data: JSON.stringify(query),
            contentType: 'application/json'
        }).done(function (response) {
            $("#reservation-search-result").html(response);
        });

    });

    $("#reservation-search-xls").click(function (e) {
        e.preventDefault();
        var from = $("#reservation-from").val();
        var to = $("#reservation-to").val();
        var state = $("#reservation-state").val();

        if (from == '') {
            from = '2000/01/01';
        } else {
            from = from.replace(/-/g, '/');
        }
        if (to == '') {
            to = '2020/12/12';
        } else {
            to = to.replace(/-/g, '/');
        }
        $("#from").val(from);
        $("#to").val(to);
        $("#state").val(state);

        $("#xls-form").submit();

    });
});