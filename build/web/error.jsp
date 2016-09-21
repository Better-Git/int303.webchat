<%-- Document   : header, Created on : 21-Sep-2016, Author     : B --%>
<%@page contentType="text/html" import="java.util.*, java.net.InetAddress, java.text.SimpleDateFormat, org.apache.commons.httpclient.*" isErrorPage="true" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="./header.jsp" %>

        <!-- CSS -->
        <link rel="stylesheet" href="./css/index.css">
        <link rel="stylesheet" href="./css/materialize.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700" >
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://code.getmdl.io/1.2.0/material.light_green-light_blue.min.css">

        <!-- JS -->
        <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/knockout/3.2.0/knockout-min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.7/js/materialize.min.js"></script>
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
                                <img style="float: left" src="./img/ic_error_outline_black_24dp.png">
                                <p class="flow-text" id="card-text4">There's something wrong.</p><br>
                                <span class="flow-text" id="card-text5">
                                    <%
                                        out.println("Error &nbsp:  &nbsp&nbsp" + pageContext.getErrorData().getStatusCode());
                                        out.println("&nbsp" + HttpStatus.getStatusText(response.getStatus()) + "<br>");
                                        if (pageContext.getException() == null) {
                                            InetAddress inetAddress = InetAddress.getLocalHost();
                                            String ip = inetAddress.getHostAddress();
                                            Date time = new Date();
                                            SimpleDateFormat frmt = new SimpleDateFormat("@ E dd-MM-yyyy  HH:mm:ss ");
                                            TimeZone zone = TimeZone.getDefault();
                                            Calendar cal = GregorianCalendar.getInstance(zone);
                                            int offsetInMillis = zone.getOffset(cal.getTimeInMillis());
                                            String offset = "GMT" + (offsetInMillis >= 0 ? "+" : "-") + String.format("%01d:%02d", Math.abs(offsetInMillis / 3600000), Math.abs((offsetInMillis / 60000) % 60));
                                            out.println("Requested&nbsp /room &nbsp:  &nbsp&nbsp" + pageContext.getErrorData().getRequestURI());
                                            System.err.println("Error : " + pageContext.getErrorData().getStatusCode() + " "
                                                    + HttpStatus.getStatusText(response.getStatus()) + " — requested /room : "
                                                    + pageContext.getErrorData().getRequestURI() + " — from : " + ip
                                                    + " — " + frmt.format(time) + offset);
                                        } else {
                                            out.println("Exception&nbsp error &nbsp:  &nbsp&nbsp" + pageContext.getException());
                                            System.err.print("Error : ");
                                            pageContext.getException().printStackTrace();
                                        }
                                    %>
                                </span>
                            </div>
                            <div class="card-action mdl-card__actions">
                                <span class="mdl-layout-spacer"></span>
                                <div style="margin-left: 20px">
                                    <a href="/yacht">
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