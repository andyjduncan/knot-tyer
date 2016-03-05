<%--
  Created by IntelliJ IDEA.
  User: andy
  Date: 05/03/2016
  Time: 10:47
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="bootstrap">
    <title>Start your RSVP!</title>
</head>

<body>
Hello ${invitation.guests*.firstName.join(" and ")}

Are you coming?
<g:form controller="rsvp" action="accept" params="[id: invitation.id]">
    <button class="btn btn-lg btn-success">Yes</button>
</g:form>
<g:form controller="rsvp" action="decline" params="[id: invitation.id]">
    <button class="btn btn-lg btn-danger">No</button>
</g:form>
</body>
</html>