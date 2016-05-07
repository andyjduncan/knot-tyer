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
<h1>Postal address</h1>
<h2>Do you know the address?</h2>

<g:form controller="invite" action="addAddress" id="${invitation.id}">
    <div class="form-group">
        <label for="line1">Address</label>
        <input id="line1" class="form-control" type="text" name="line1" placeholder="line 1" required="required"/>
        <input class="form-control" type="text" name="line2" placeholder="line 2"/>
        <input class="form-control" type="text" name="line3" placeholder="line 3"/>
        <input class="form-control" type="text" name="line4" placeholder="line 4"/>
        <input class="form-control" type="text" name="line5" placeholder="line 5"/>
    </div>

    <div class="form-group">
        <label for="postalCode">Postal code</label>
        <input id="postalCode" class="form-control" type="text" name="postalCode" placeholder="postal code"
               required/>
    </div>

    <div class="form-group">
        <label for="country">Country</label>
        <input id="country" class="form-control" type="text" name="country" placeholder="country"/>
    </div>
    <button class="btn btn-primary" type="submit">Yes</button>
</g:form>
<g:form controller="invite" action="showAddEmail" id="${invitation.id}">
    <div class="form-group">
        <button class="btn" type="submit">No</button>
    </div>
</g:form>
</body>
</html>