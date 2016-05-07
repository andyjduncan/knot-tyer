<%--
  Created by IntelliJ IDEA.
  User: andy
  Date: 05/03/2016
  Time: 13:59
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="default">
    <title>Sorry to hear that!</title>
</head>

<body>
Sorry to hear that :(

<g:form action="accept" id="${invitation.id}">
    <button class="btn btn-lg btn-success">
        <g:if test="${invitation.guests.size() == 1}">
            I've changed my mind!
        </g:if>
        <g:else>
            We've changed our minds!
        </g:else>
    </button>
</g:form>
</body>
</html>