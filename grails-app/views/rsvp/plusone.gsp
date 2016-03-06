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

<g:form controller="rsvp" action="noPlusOne" id="${invitation.id}">
    <button class="btn btn-lg btn-success">Nope, just me</button>
</g:form>
<g:form controller="rsvp" action="plusOne" id="${invitation.id}">
    <button class="btn btn-lg btn-success">Yep</button>
</g:form>

</body>
</html>