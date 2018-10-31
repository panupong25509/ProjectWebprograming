<%-- 
    Document   : Withdraw
    Created on : Oct 10, 2018, 1:48:44 PM
    Author     : Joknoi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Withdraw</title>
    </head>
    <body>
        <h1>Withdraw</h1>
        <p>Balance: ${account.balance}</p>
        <form action="Withdraw" method=post>
            <table>
                <tr>
                    <td>Money</td>
                    <td><input type="text" name="money" required></td>
                    <td><input type="submit" value="OK"></td>
                </tr>
            </table>
        </form>
    </body>
</html>
