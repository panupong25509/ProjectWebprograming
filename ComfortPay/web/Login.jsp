<%-- 
    Document   : Login.jsp
    Created on : Nov 4, 2018, 4:03:49 AM
    Author     : crtiexx
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <title>Login Comfort Pay</title>
    </head>
    <body>
        <div class="col-8 mx-auto mt-5 text-center">
            <h1>Login Comfort Pay</h1>
            <form action="Login">
                <br>
                <input type="text" placeholder="Enter Username" name="username" required>
                <br>
                <br>
                <input type="password" placeholder="Enter Password" name="password" required>
                <br>
                <br>
                <input type="hidden" name="path" value=${path}>
                <button type="submit">Login</button>
            </form>
        </div>
    </body>
</html>
