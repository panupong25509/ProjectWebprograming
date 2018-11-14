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
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

        <title>My Account</title>
    </head>
    <body>
        <jsp:include page="Components/Navbar.jsp"/>
        <div style="padding-top: 130px;">
            <h1>My Account</h1>
            <p>${account.fname} ${account.lname}</p>
            <p>${account.level}</p>
            <p>${account.score}</p>
        </div>


        <p></p>
    </body>
</html>
