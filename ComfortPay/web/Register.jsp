<%-- 
    Document   : Register
    Created on : Nov 15, 2018, 12:35:13 AM
    Author     : Joknoi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
    </head>
    <body>
                <jsp:include page="Components/Navbar.jsp"/>
        <div class="col-8 mx-auto text-center" style="padding-top: 130px;">
            <h1>Register Comfort Pay</h1>
            <form action="RegisterAccount">
                <br>
                <input type="text" placeholder="Enter Username" name="username" required>
                <br>
                <br>
                <input type="password" placeholder="Enter Password" name="password" required>
                <br>
                <br>
                <input type="password" placeholder="Enter Password" name="passwordAgain" required>
                <br>
                <br>
                <input type="password" placeholder="Enter Password" name="passwordAgain" required>
                <br>
                <br>
                <button type="submit">Login</button>
            </form>
        </div>
    </body>
</html>
