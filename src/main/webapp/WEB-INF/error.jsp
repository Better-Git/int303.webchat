<%-- Document   : header, Created on : 21-Sep-2016, Author     : B --%>
<%@page contentType="text/html" isErrorPage="true" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="./header.jsp" %>

        <!-- JS -->
        <script src="https://code.getmdl.io/1.2.0/material.min.js" defer></script>

        <title>Oops !</title>
    </head>
    <body>
        <main id="content">
            <div class="container">
                <div class="row">
                    <div class="col s12">
                        <div class="mdl-card mdl-shadow--2dp card sticky-action" id="card">
                            <div class="card-content" id="card-content">
                                <img style="float: left; width: 96px" src="./img/ic_error_outline_black_48dp.png">
                                <p class="flow-text" id="card-text4">There's something wrong.</p><br>
                                <span class="flow-text" id="card-text5">
                                    <%
                                        out.println("Error &nbsp:  &nbsp&nbsp" + pageContext.getErrorData().getStatusCode());
                                        out.println("&nbspInternal Server Error<br>");
                                        out.println("Exception&nbsp error &nbsp:  &nbsp&nbsp" + pageContext.getException());
                                    %>
                                </span>
                            </div>
                            <div class="card-action mdl-card__actions">
                                <span class="mdl-layout-spacer"></span>
                                <div style="margin-left: 20px">
                                    <a href="/">
                                        <button class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent btn modal-trigger" id="join">Go back to Yacht</button>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>
        <footer class="page-footer">
            <div class="container">
                <p class="white-text">INT303 Web Programming Project &nbsp;,&nbsp; © 2016</p>
            </div>
        </footer>
    </body>
</html>