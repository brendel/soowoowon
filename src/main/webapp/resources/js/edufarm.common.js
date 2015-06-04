var validator = {
    isVailidLength: function (s, min, max) {
        if (s.length > max || s.length < min) {
            return false;
        }
        else {
            return true;
        }
    },
    isVailidEmail: function (str) {
        return this.isVailidLength(str, 6, 80) && (/^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/.test(str));
    },
    isValidDate: function (str) {
        return /^\d{4}\-\d{1,2}\-\d{1,2}$/.test(str);
    }
}

var collectFormData = function (fields) {
    var data = {};
    for (var i = 0; i < fields.length; i++) {
        var $item = $(fields[i]);
        data[$item.attr('name')] = $item.val();
    }
    return data;
}

var getQueryVariable = function (variable) {
    var query = window.location.search.substring(1);
    var vars = query.split("&");
    for (var i = 0; i < vars.length; i++) {
        var pair = vars[i].split("=");
        if (pair[0] == variable) {
            return pair[1];
        }
    }
    return (false);
}

$(document).ready(function () {
    if (getQueryVariable("accessDenied")) {
        alert("권한이 없습니다.")
    }
});