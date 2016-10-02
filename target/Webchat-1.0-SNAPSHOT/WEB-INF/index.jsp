<%-- Document   : index, Created on : 21-Sep-2016, Author     : B --%>
<%@page contentType="text/html" isErrorPage="false" pageEncoding="UTF-8" errorPage="./error.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="./header.jsp" %>

        <!-- JS -->
        <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/knockout/3.2.0/knockout-min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.7/js/materialize.min.js"></script>
        <script src="https://code.getmdl.io/1.2.0/material.min.js" defer></script>
        <script src="https://www.google.com/recaptcha/api.js" async defer></script>
        <script>
            function launch() {
                $('#launch').prop('disabled', false);
            }

            function validate() {
                event.preventDefault();
                event.stopPropagation();
                var rm = $('#room').val();
                var un = $('#username').val();
                var plc = $('#password').prop("disabled");
                var pw = $('#password').val();
                if (plc === true) {
                    if (rm === '') {
                        alert("Please enter your room number, first.\n" +
                                "Must be at 3 to 6 letters or numbers.");
                        $('#room').focus();
                    } else if (rm !== '' && un === '') {
                        alert("Please enter your name, first.\n" +
                                "Must be at least 1 letter or number.");
                        $('#username').focus();
                    } else if (rm !== '' && un !== '') {
                        $('#recaptcha').openModal();
                        $('#launch').click(function () {
                            $('#chatroom').submit();
                            grecaptcha.reset();
                        });
                    }
                } else {
                    if (rm === '') {
                        alert("Please enter your room number, first.\n" +
                                "Must be at 3 to 6 letters or numbers.");
                        $('#room').focus();
                    } else if (rm !== '' && un === '') {
                        alert("Please enter your name, first.\n" +
                                "Must be at least 1 letter or number.");
                        $('#username').focus();
                    } else if (rm !== '' && un !== '' && pw === '') {
                        alert("Please enter your password, first.\n" +
                                "Must be at least 3 characters.");
                        $('#password').focus();
                    } else if (rm !== '' && un !== '' && pw !== '') {
                        $('#recaptcha').openModal();
                        $('#launch').click(function () {
                            $('#chatroom').submit();
                            grecaptcha.reset();
                        });
                    }
                }
            }

            function unlaunch() {
                $('#launch').prop('disabled', true);
            }

            $(function () {
                document.exitFullscreen();
                screen.orientation.unlock();
                unlaunch();
            });
        </script>

        <title>Yacht – Just chat, that's it.</title>
    </head>
    <body>
        <ul class="slideshow">
            <li><span><!-- Image 1 --></span></li>
            <li><span><!-- Image 2 --></span></li>
            <li><span><!-- Image 3 --></span></li>
            <li><span><!-- Image 4 --></span></li>
            <li><span><!-- Image 5 --></span></li>
            <li><span><!-- Image 6 --></span></li>
        </ul>
        <main id="content">
            <div class="container">
                <div class="row">
                    <div class="col s12">
                        <div class="mdl-card mdl-shadow--2dp card sticky-action" id="card">
                            <div class="card-content" id="card-content">
                                <img id="index-logo" src="./img/ic_directions_boat_black_48dp.png">
                                <div class="card-title" id="card-title">Yacht</div><br>
                                <p class="flow-text" id="card-text1">A web chat that relies on speed, security and simplicity.<br>Just chat, that's it.</p>
                            </div>
                            <div class="card-action mdl-card__actions" id="card-border">
                                <form id="chatroom" name="chatroom" action="/FormHandle" method="post" onsubmit="validate();">
                                    <span id="spc"></span>
                                    <div id="card-form">
                                        <i class="material-icons" id="icon">forum</i>
                                        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label" id="input" style="width: 2cm;">
                                            <input class="mdl-textfield__input" id="room" name="room" type="text" minlength="3" maxlength="6" pattern="^[a-zA-Z0-9]{2,6}$" title="Please use only alphanumeric characters (A-Z, a-z, 0-9) 3 to 6 letters for a /room.">
                                            <label class="mdl-textfield__label" for="room">/room</label>
                                            <span class="mdl-textfield__error">Alphanumeric 3 to 6 letters!</span>
                                        </div>
                                    </div>
                                    <span id="line-break-s"></span>
                                    <div id="card-form">
                                        <i class="material-icons" id="icon">account_circle</i>
                                        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label" id="input" style="width: 3.2cm">
                                            <input class="mdl-textfield__input" id="username" name="username" type="text" minlength="1" pattern="^[a-zA-Z0-9]{1,}$" title="Please use only alphanumeric characters (A-Z, a-z, 0-9) at least 1 letter for a name.">
                                            <label class="mdl-textfield__label" for="username">Name</label>
                                            <span class="mdl-textfield__error">Alphanumeric at least 1 letter!</span>
                                        </div>
                                    </div>
                                    <span id="line-break-m"></span>
                                    <label class="mdl-icon-toggle mdl-js-icon-toggle mdl-js-ripple-effect" id="icon-toggle" for="mk-private" onclick="mkPrivate();">
                                        <input class="mdl-icon-toggle__input" id="mk-private" name="mk-private" type="checkbox" value="yes">
                                        <i class="mdl-icon-toggle__label material-icons">vpn_key</i>
                                    </label>
                                    <input data-bind="mkPassword: {value: password}">
                                    <script src="./js/index.js"></script>
                                    <span id="line-break-m"></span>
                                    <span id="line-break"></span>
                                    <!--
                                    <button class="mdl-chip mdl-color--light-blue-A200" type="button">
                                        <svg id="icon-button" viewBox="0 0 24 24">
                                        <path fill="#000000" d="M4,4H10V10H4V4M20,4V10H14V4H20M14,15H16V13H14V11H16V13H18V11H20V13H18V15H20V18H18V20H16V18H13V20H11V16H14V15M16,15V18H18V15H16M4,20V14H10V20H4M6,6V8H8V6H6M16,6V8H18V6H16M6,16V18H8V16H6M4,11H6V13H4V11M9,11H13V15H11V13H9V11M11,6H13V10H11V6M2,2V6H0V2A2,2 0 0,1 2,0H6V2H2M22,0A2,2 0 0,1 24,2V6H22V2H18V0H22M2,18V22H6V24H2A2,2 0 0,1 0,22V18H2M22,22V18H24V22A2,2 0 0,1 22,24H18V22H22Z" />
                                        </svg>
                                        <span class="mdl-chip__text">Scan QR code for /room</span>
                                    </button>
                                    -->
                                    <span id="line-break-s"></span>
                                    <span class="mdl-layout-spacer"></span>
                                    <div style="float: right">
                                        <span>
                                            <button class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent btn modal-trigger" id="join" data-target="recaptcha">Join</button>
                                        </span>
                                        <i class="material-icons activator" id="help">help</i>
                                    </div>
                                </form>
                            </div>
                            <div class="card-reveal">
                                <span class="card-title">Meet Yacht<i class="material-icons right" id="close">close</i></span>
                                <p class="flow-text" id="card-text2">Yacht intend to be a very fast and simple way to start a chat. 
                                    Of course, no need to create an account or login. Just enter your room code or leave blank to random one, and set your name. 
                                    That's it, then you can chat anywhere and anytime you want.</p>
                                <i class="material-icons" style="margin-left: 3px">desktop_windows</i>&nbsp;&nbsp;&nbsp;
                                <i class="material-icons">computer</i>&nbsp;&nbsp;&nbsp;
                                <i class="material-icons">tablet</i>&nbsp;&nbsp;&nbsp;
                                <i class="material-icons">smartphone</i>&nbsp;&nbsp;&nbsp;
                                <i class="material-icons">watch</i><br>
                                <p class="flow-text" id="card-text3">
                                    * Yacht is still being a beta software, it may not function or display correctly. We still recommend to use only supported browsers, 
                                    Google Chrome (version 40 or later), Apple Safari (version 9 or later) and Internet Explorer (version 10 or later).
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>
        <%  try {
                String verify = (String) request.getAttribute("verify");
                if ((!verify.equals("") || !verify.equals(null)) && verify.equals("false")) { %>
        <script>
                                        $(document).ready(function () {
                                            Materialize.toast('Your captcha is not verified.<br>Please try again. &#128581', 5000);
                                        });
        </script>
        <%      }
            } catch (NullPointerException e) {
            }%>
        <footer class="page-footer">
            <div class="container">
                <p class="white-text">INT303 Web Programming Project &nbsp;,&nbsp; &#169; 2016</p>
            </div>
        </footer>
        <div class="modal bottom-sheet" id="recaptcha">
            <div class="modal-content">
                <h4>Are you a robot?</h4>
                <p>Please help us to protect robots and spams by verifying yourself.</p>
                <div class="g-recaptcha" data-callback="launch" data-expired-callback="unlaunch" data-sitekey="6LcSyAcUAAAAAJQHcNS-7mxklAXSOnPxjLEdSfyd" data-theme="dark" form="chatroom"></div>
            </div><hr>
            <div class="modal-footer">
                <button class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--colored modal-action modal-close btn" id="launch" form="chatroom" type="submit">Chat now</button>
            </div>
        </div>
    </body>
</html>