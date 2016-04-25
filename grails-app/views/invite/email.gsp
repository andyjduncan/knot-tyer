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
<h1>Email address</h1>

<h2>Do you know an email address?</h2>

<g:form class="form-inline" controller="invite" action="addEmail" id="${invitation.id}">
    <div class="form-group">
        <label class="sr-only" for="emailAddress">Email address</label>
    <input id="emailAddress" class="form-control" type="email" name="emailAddress" placeholder="email@example.com" required />
    </div>
    <button class="btn btn-primary" type="submit">Yes</button>
</g:form>
<g:form controller="invitation" action="index" method="get">
    <button class="btn">No</button>
</g:form>
</body>
</html>