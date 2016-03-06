<%--
  Created by IntelliJ IDEA.
  User: andy
  Date: 05/03/2016
  Time: 21:11
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="bootstrap">
    <title>Anyone else?</title>
</head>

<body>
Who is coming?

<g:form controller="rsvp" action="chooseGuests" id="${invitation.id}">
    <button class="btn btn-lg" name="guestIds" value="${invitation.guests*.id.join(',')}">We both are!</button>
    <g:each in="${invitation.guests}">
        <button class="btn btn-lg" name="guestIds" value="${it.id}">Only ${it.firstName} can make it</button>
    </g:each>
</g:form>

</body>
</html>