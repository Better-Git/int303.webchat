document.documentElement.setAttribute('data-platform', navigator.platform);
document.documentElement.setAttribute('data-useragent', navigator.userAgent);

$(function () {
    if ($(window).width() <= 280) {
        $('#close').hide();
    }
});

$(window).resize(function () {
    if ($(window).width() <= 280) {
        $('#close').hide();
    } else {
        $('#close').show();
    }
});

ko.bindingHandlers.mkPassword = {
    init: function (element, valueAccessor, allBindingsAccessor, data, context) {
        var $input = $(element),
            $enclosingDiv = $('<div>').insertAfter($input),
            $label = $('<label>'),
            $span = $('<span>'),
            id = "password", params = valueAccessor();
        $input.attr('disabled', true);
        $input.attr('id', id);
        $input.attr('minlength', "3");
        $input.attr('name', id);
        $input.attr('pattern', ".{3,}");
        $input.attr('title', "Please use any characters at least 3 letters for a password.");
        $input.attr('type', "password");
        $label.attr('for', id).text("Password for private room");
        $span.attr('for', id).text("Password should be at least 3 letters!");
        $enclosingDiv.attr('id', "input");
        $enclosingDiv.attr('style', "width: 5cm");
        $input.addClass('mdl-textfield__input');
        $label.addClass('mdl-textfield__label');
        $span.addClass('mdl-textfield__error');
        $enclosingDiv.addClass("mdl-textfield mdl-js-textfield").append($input).append($label).append($span);
        ko.bindingHandlers.value.init(element, function () {
            return params.value;
        }, allBindingsAccessor, data, context);
    },
    update: function (element, valueAccessor, allBindingsAccessor, data, context) {
        var params = valueAccessor(), value = params.value();
        ko.bindingHandlers.value.update(element, function () {
            return params.value;
        }, allBindingsAccessor, data, context);
        $(element).parent()[value ? 'addClass' : 'removeClass']('is-dirty');

    }
};

var vm = new MyViewModel();
ko.applyBindings(vm);

function MyViewModel() {
    var self = this;
    self.password = ko.observable('');
    self.resetForm = function () {
        self.password("");
    };
}

function mkPrivate() {
    $('#mk-private').change(function () {
        if ($('#mk-private').is(':checked') === true) {
            $('#password').attr('disabled', false);
        } else {
            $('#password').attr('disabled', true);
            vm.resetForm();
            $('#password').parent().removeClass('is-invalid');
        }
    });
}

function validate() {
    event.preventDefault();
    event.stopPropagation();
    var x = document.forms["chatroom"]["room"].value;
    var y = document.forms["chatroom"]["username"].value;
    if (x === null || x === "" || x.checkValidity() === false) {
        alert("Please enter your room code correctly.");
        document.getElementById("room").focus();
    }
    $('#room').change(function (e) {
        $('#username').focus();
    });
    $('#username').on("focus", function () {
        if (y === null || y === "") {
            alert("Please enter your name.");
            document.getElementById("username").focus();
        }
    });
    $('#username').change(function (e) {
        $('#join').focus();
    });
    if (!(x === null || x === "") && !(y === null || y === "")) {
        $(document).ready(function () {
            $('.modal-trigger').leanModal();
        });
    }
}