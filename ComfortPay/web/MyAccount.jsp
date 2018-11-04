<%-- 
    Document   : MyAccount
    Created on : Nov 4, 2018, 4:23:15 AM
    Author     : crtiexx
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My Account</title>
    </head>
    <body>
        <jsp:include page="Components/Navbar.jsp"/>
        <h1>My Account</h1>
        <p>${account.username}</p>
        <p>${account.degree}</p>
        <p>${account.score}</p>
        <p></p>
    </body>
</html>
