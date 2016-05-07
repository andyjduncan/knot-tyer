<%--
  Created by IntelliJ IDEA.
  User: andy
  Date: 05/03/2016
  Time: 21:11
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="default">
    <title>Anyone else?</title>
</head>

<body>
What's their name?

<g:form controller="rsvp" action="addPlusOne" id="${invitation.id}">
    <input class="required-indicator" type="text" name="firstName" placeholder="firstName" required />
    <input class="required-indicator" type="text" name="lastName" placeholder="lastName" required />
    <button class="btn btn-lg">There</button>
</g:form>

</body>
</html>