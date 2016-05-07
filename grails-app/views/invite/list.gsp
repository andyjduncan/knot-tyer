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
    <title>Guests</title>
</head>

<body>
<header class="w3-container w3-card-4 w3-yellow">
    <h1>All guests</h1>
</header>
<div class="w3-container">
    <table id="guestsTable" class="w3-card-2">
        <thead>
            <tr>
                <th>
                    First Guest
                </th>
                <th>
                    Second Guest
                </th>
                <th>
                    Status
                </th>
                <th>
                    RSVP Link
                </th>
            </tr>
        </thead>
        <tbody>
            <g:each in="${invitations}">
                <tr>
                    <td>${it.guests.first().name}</td>
                    <td>
                        <g:if test="${it.guests.size() > 1}">
                            ${it.guests.last().name}
                        </g:if>
                    </td>
                    <td>
                        ${it.status}
                    </td>
                    <td>
                        <g:link controller="rsvp" action="rsvp" id="${it.id}">${it.id}</g:link>
                    </td>
                </tr>
            </g:each>
        </tbody>
    </table>
</div>
<asset:script>
    $(document).ready(function() {
        $('#guestsTable').DataTable({
            'stripeClasses': ['w3-khaki', 'w3-sand']
        });
    });
</asset:script>
</body>
</html>