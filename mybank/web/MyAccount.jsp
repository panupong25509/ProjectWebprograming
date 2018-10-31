<%-- 
    Document   : MyAccount
    Created on : Oct 10, 2018, 11:16:42 AM
    Author     : Joknoi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My Account</title>
    </head>
    <body>
        <h1>My Account</h1>
        <p style="color: red">${error}</p>
        <h2>${account.accountname}</h2>
        <p>Balance: ${account.balance}</p>
        <a href="DepositPage"><p>Deposit</p></a>
        <a href="WithdrawPage"><p>Withdraw</p></a>
        <a href="History"><p>History</p></a>
        <a href="Logout"><button>Logout</button></a>
    </body>
</html>
