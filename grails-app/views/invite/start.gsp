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
    <title>First guest</title>
</head>

<body>
<div>
    <h1>New invitation</h1>

    <h2>What's the name of the first guest?</h2>

    <g:form class="form-inline" controller="invite" action="add">
        <div class="form-group">
            <label class="sr-only" for="firstName">First name</label>
            <input id="firstName" class="form-control" type="text" name="firstName" placeholder="First name"
                   required />
        </div>

        <div class="form-group">
            <label class="sr-only" for="lastName">Last name</label>
            <input id="lastName" class="form-control" type="text" name="lastName" placeholder="Last name"
                   required />
        </div>
        <button class="btn btn-primary">There</button>
    </g:form>
</div>
</body>
</html>