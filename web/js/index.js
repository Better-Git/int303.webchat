document.documentElement.setAttribute('data-useragent', navigator.userAgent);
document.documentElement.setAttribute('data-platform', navigator.platform );

function validate() {
    event.preventDefault();
    event.stopPropagation();
    var x = document.forms["chatroom"]["room"].value;
    var y = document.forms["chatroom"]["username"].value;
    if (x === null || x === "" || x.checkValidity() === false) {
        alert("Please enter your room code correctly.");
        document.getElementById("room").focus();
    }
    $('#room').change(function(e) {
        $('#username').focus();
    });
    $('#username').on("focus", function() {
        if (y === null || y === "") {
            alert("Please enter your name.");
            document.getElementById("username").focus();
        }
    });
    $('#username').change(function(e) {
        $('#join').focus();
    });
    if (!(x === null || x === "") && !(y === null || y === "")) {
        $(document).ready(function() {
            $('.modal-trigger').leanModal();
        });
    }
}