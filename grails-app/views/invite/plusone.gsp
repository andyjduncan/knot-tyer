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
<h1>Plus one</h1>

<h2>Is there a plus one?</h2>

<g:form class="form-inline" controller="invite" action="addPlusOne" id="${invitation.id}">
    <div class="form-group">
        <label class="sr-only" for="firstName">First name</label>
        <input id="firstName" class="form-control" type="text" name="firstName" placeholder="First name"
               required />
    </div>

    <div class="form-group">
        <label class="sr-only" for="lastName">Last name</label>
        <input id="lastName" class="form-control" type="text" name="lastName" placeholder="Last name"
               required="required" />
    </div>
    <button class="btn btn-primary" type="submit">Yes</button>
</g:form>
<g:form controller="invite" action="showAddAddress" id="${invitation.id}" method="get">
    <button class="btn" type="submit">No</button>
</g:form>
</body>
</html>