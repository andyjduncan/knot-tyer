<%--
  Created by IntelliJ IDEA.
  User: andy
  Date: 05/03/2016
  Time: 10:47
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="default">
    <title>Start your RSVP!</title>
</head>

<body class="w3-container w3-center">
<header class="w3-container w3-card">
    <h1>Hello ${invitation.guests*.firstName.join(" and ")}</h1>
</header>

<div class="w3-third w3-card-2 w3-margin-top">
    <h2>Are you coming?</h2>

    <div class="w3-row">
        <g:form controller="rsvp" action="accept" params="[id: invitation.id]">
            <button class="w3-btn w3-green w3-half">Yes</button>
        </g:form>
        <g:form controller="rsvp" action="decline" params="[id: invitation.id]">
            <button class="w3-btn w3-red w3-half">No</button>
        </g:form>
    </div>
</div>
</body>
</html>