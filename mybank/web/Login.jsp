<%-- 
    Document   : Login
    Created on : Oct 10, 2018, 10:53:08 AM
    Author     : Joknoi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <h1>Login</h1>
        <p style="color: red">${error}</p>
        <form action="Login" method="post">
            <table>
                <tr>
                    <td>Account ID</td>
                    <td><input type="text" name="id" required></td>
                </tr>
                <tr>
                    <td>PIN</td>
                    <td><input type="text" name="pin" required></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Login"></td>
                </tr>
            </table>
        </form>
    </body>
</html>
