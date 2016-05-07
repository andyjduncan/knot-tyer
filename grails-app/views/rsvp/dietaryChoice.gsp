<%--
  Created by IntelliJ IDEA.
  User: andy
  Date: 05/03/2016
  Time: 20:32
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="default">
    <title>What are you eating!</title>
</head>

<body>
Does ${guest.firstName} have any special dietary requirements?

<g:form controller="rsvp" action="chooseDietary" params="[id: invitation.id, guestId: guest.id]">
    <g:each in="${knot.tyer.DietaryRequirement}">
        <button name="chosenRequirement" value="${it}">${it}</button>
    </g:each>
</g:form>
</body>
</html>