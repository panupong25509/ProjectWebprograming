<%-- 
    Document   : Deposit
    Created on : Oct 10, 2018, 11:39:33 AM
    Author     : Joknoi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Deposit</title>
    </head>
    <body>
        <h1>Deposit</h1>
        <p>Balance: ${account.balance}</p>
        <form action="Deposit">
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
