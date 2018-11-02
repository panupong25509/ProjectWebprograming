<%-- 
    Document   : Login
    Created on : Nov 2, 2018, 3:20:46 AM
    Author     : Joknoi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Login</h1>
        <form action="Login">
            <table>
                <tr>
                    <td>Username</td>
                    <td><input type="text" required name="username"></td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td><input type="text" required name="password"></td>
                </tr>
                <tr>
                    <td></td>
                    <td><button href="Login">Login</button></td>
                </tr>
            </table>
        </form>
    </body>
</html>
