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
Are you bringing anyone else?

<g:form controller="rsvp" action="addPlusOne" id="${invitation.id}">
    <input class="required-indicator" type="text" name="firstName" placeholder="firstName" required />
    <input class="required-indicator" type="text" name="lastName" placeholder="lastName" required />
    <button class="btn btn-lg">Yep</button>
</g:form>
</body>
</html>