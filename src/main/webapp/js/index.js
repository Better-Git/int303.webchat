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
        $enclosingDiv.attr('id', "input-pswd");
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

var vm = new myViewModel();
ko.applyBindings(vm);

function myViewModel() {
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