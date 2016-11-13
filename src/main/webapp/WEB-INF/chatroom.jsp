<%@page import="model.Content"%>
<%@page import="model.Room"%>
<%@page import="java.util.List"%>
<%@page import="com.google.appengine.api.users.UserServiceFactory"%>
<%@page import="com.google.appengine.api.users.User"%>
<%@page import="com.google.appengine.api.users.UserService"%>
<%@page import="com.googlecode.objectify.Key"%>
<%@page import="com.googlecode.objectify.ObjectifyService"%>
<%@page contentType="text/html" isErrorPage="false" pageEncoding="UTF-8" errorPage="./error.jsp"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%-- <%@taglib prefix="ti" uri="/WEB-INF/time_tld.tld"%> --%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="./header.jsp" %>
        <link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
        <link rel="stylesheet" href="https://code.getmdl.io/1.2.0/material.light_green-light_blue.min.css" id="css">
        <link rel="stylesheet" href="./css/chatroom.css">

        <!-- JS -->
        <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.7/js/materialize.min.js"></script>
        <script src="https://code.getmdl.io/1.2.0/material.min.js" defer></script>
        <script>
            $(function () {
//                window.history.replaceState('', '', './${fn:escapeXml(roomName)}');
            });

            $(window).bind('beforeunload', function () {
//                return false;
            });
        </script>

        <title>Yacht / ${fn:escapeXml(roomName)}</title>
    </head>
    <body>
        <%
            pageContext.setAttribute("roomName", request.getParameter("roomName"));

            //[START datastore]
            // Create the correct Ancestor key
            Key<Room> theRoom = Key.create(Room.class, request.getParameter("roomName"));

            // Run an ancestor query to ensure we see the most up-to-date
            // view of the Contents belonging to the selected Room.
            List<Content> contents = ObjectifyService.ofy()
                    .load()
                    .type(Content.class) // We want only Contents
                    .ancestor(theRoom) // Anyone in this room
                    .order("-date") // Most recent first - date is indexed.
                    .limit(500) // Only show 500 of them.
                    .list();
        %>
        <div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
            <ul id="slide-out" class="side-nav">
                <li><div class="userView" id="uv" style="background: #8BC34A;">
                        <i class="material-icons">account_circle</i>
                        <span class="chat-title">${requestScope.username}</span>
                    </div></li>
                <li><ul class="collapsible collapsible-accordion">
                        <li><a class="collapsible-header shift1">Theme<i class="material-icons">expand_more</i></a>
                            <div class="collapsible-body">
                                <ul style="clear: both">
                                    <li id="chat-theme"><a class="waves-effect light-green" id="clg" onclick="theme(this);"></a></li>
                                    <li id="chat-theme"><a class="waves-effect green" id="cg" onclick="theme(this);"></a></li>
                                    <li id="chat-theme"><a class="waves-effect red" id="cr" onclick="theme(this);"></a></li>
                                    <li id="chat-theme"><a class="waves-effect deep-orange" id="cdo" onclick="theme(this);"></a></li>
                                    <li id="chat-theme"><a class="waves-effect lime" id="cl" onclick="theme(this);"></a></li>
                                    <li id="chat-theme"><a class="waves-effect teal" id="ct" onclick="theme(this);"></a></li>
                                    <li id="chat-theme"><a class="waves-effect pink" id="cp" onclick="theme(this);"></a></li>
                                    <li id="chat-theme"><a class="waves-effect brown" id="cbr" onclick="theme(this);"></a></li>
                                    <li id="chat-theme"><a class="waves-effect yellow" id="cy" onclick="theme(this);"></a></li>
                                    <li id="chat-theme"><a class="waves-effect cyan" id="cc" onclick="theme(this);"></a></li>
                                    <li id="chat-theme"><a class="waves-effect purple" id="cpu" onclick="theme(this);"></a></li>
                                    <li id="chat-theme"><a class="waves-effect grey" id="cgr" onclick="theme(this);"></a></li>
                                    <li id="chat-theme"><a class="waves-effect amber" id="ca" onclick="theme(this);"></a></li>
                                    <li id="chat-theme"><a class="waves-effect light-blue" id="clb" onclick="theme(this);"></a></li>
                                    <li id="chat-theme"><a class="waves-effect deep-purple" id="cdp" onclick="theme(this);"></a></li>
                                    <li id="chat-theme"><a class="waves-effect" id="cbart"  onclick="theme(this);">
                                            <img id="bart" src="https://farm9.staticflickr.com/8623/buddyicons/127203165@N08_r.jpg?1424820036">
                                        </a></li>
                                    <li id="chat-theme"><a class="waves-effect orange" id="co" onclick="theme(this);"></a></li>
                                    <li id="chat-theme"><a class="waves-effect blue" id="cb" onclick="theme(this);"></a></li>
                                    <li id="chat-theme"><a class="waves-effect indigo" id="ci" onclick="theme(this);"></a></li>
                                    <li id="chat-theme"><a class="waves-effect blue-grey" id="cbg" onclick="theme(this);"></a></li>
                                </ul><br><br><br><br><br>
                            </div>
                        </li>
                    </ul></li>
                <li><div class="divider"></div></li>
                <li><a class="waves-effect modal-trigger" data-target="shareit"><i class="material-icons">share</i>Share</a></li>
                <li><a class="waves-effect modal-trigger" data-target="imout"><i class="material-icons">highlight_off</i>Log out</a></li>
                <li><a class="subheader">&copy;&nbsp; Yacht,&nbsp; 2016</a></li>
                <li><a class="subheader" id="fox">The Simpsons<sup id="trade">TM</sup> and &copy; Fox.</a></li>
            </ul>
            <header class="mdl-layout__header" id="hd">
                <div class="mdl-layout__header-row">
                    <a data-activates="slide-out" class="button-collapse chat-drawer">
                        <i class="material-icons">menu</i>
                    </a>
                    <div class="mdl-layout-spacer"></div>
                    <span class="mdl-layout-title">${fn:escapeXml(roomName)}</span>
                    <div class="mdl-layout-spacer"></div>
                </div>
            </header>
        </div>
        <script src="./js/chatroom.js"></script>
        <main class="mdl-layout__content" id="content">
            <div class="page-content">
                <div id="spcr"></div>
                <%
                    for (Content c : contents) {
                        pageContext.setAttribute("content", c.content);
                        if (c.sender != null) {
                            pageContext.setAttribute("username", c.sender);
                        }
                %>
                <!-- Your content goes here -->
                <div class="card mdl-shadow--2dp" style="margin: 0 auto 20px auto; width: 500px;">
                    <div class="mdl-card__supporting-text">
                        <%-- <ti:getTime /> --%>
                        <p style="font-size: 20px; margin-top: 15px;"><b>${fn:escapeXml(username)}&nbsp;&nbsp;:</b>&nbsp;&nbsp;${fn:escapeXml(content)}</p>
                    </div>
                </div>
                <% } %>
            </div>
        </main>
        <footer class="page-footer white">
            <form class="shift2" action="ChatHandle" method="post">
                <div class="mdl-textfield mdl-js-textfield chat-message">
                    <input class="mdl-textfield__input chat-input" id="message" name="message" type="text">
                    <label class="mdl-textfield__label chat-input" for="message">Type any message...</label>
                    <input type="hidden" name="roomName" value="${requestScope.roomName}">
                    <input type="hidden" name="username" value="${requestScope.username}">
                </div>
                <button class="mdl-button mdl-js-button mdl-button--icon shift3" type="submit">
                    <i class="material-icons">send</i>
                </button>
                <!--
                <div class="fixed-action-btn click-to-toggle" id="add">
                    <a class="mdl-button mdl-js-button mdl-button--icon shift4">
                        <i class="material-icons">add</i>
                    </a>
                    <ul>
                        <li><a class="btn-floating blue" id="add-child"><i class="material-icons">insert_drive_file</i></a></li>
                        <li><a class="btn-floating cyan" id="add-child"><i class="material-icons">insert_photo</i></a></li>
                        <li><a class="btn-floating red" id="add-child"><i class="material-icons">gps_fixed</i></a></li>                        
                    </ul>
                </div>
                -->
            </form>
        </footer>
        <div class="modal" id="shareit">
            <div class="modal-content">
                <h4>To share this /room</h4>
                <p><strong>1.)</strong>&nbsp; Go to &nbsp;<strong>goo.gl/X2BpkW</strong></p>
                <p><strong>2.)</strong>&nbsp; Give this /room code : &nbsp;<strong>${fn:escapeXml(roomName)}</strong></p>
                <%--
                ${requestScope.password == null || "" ? "" : "<p><strong>3.)</strong>&nbsp; Tell this password to your friends :<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong><u>????????</u></strong></p>"}
                --%>
            </div>
            <div class="modal-footer">
                <a class="modal-action modal-close waves-effect btn-flat">Got it</a>
            </div>
        </div>
        <div class="modal" id="imout">
            <div class="modal-content">
                <h4>Are you sure to leave?</h4>
                <!--
                <p>If you're the only user left in this room, all of chat records will be deleted !</p>
                -->
            </div>
            <div class="modal-footer">
                <a class="modal-action modal-close waves-effect btn-flat">No</a>
                <a href="QuitHandle" class="modal-action modal-close waves-effect btn-flat">Yes</a>
            </div>
        </div>
    </body>
</html>