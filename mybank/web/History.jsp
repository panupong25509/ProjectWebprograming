<%-- 
    Document   : History
    Created on : Oct 10, 2018, 2:10:19 PM
    Author     : Joknoi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>History</title>
    </head>
    <body>
        <h1>History</h1>
        <table>
            <tr>
                <th>No.</th>
                <th>Status</th>
                <th>Amount</th>
                <th>Balance</th>
            </tr>
            <c:forEach items="${history}" var="h" varStatus="n">
                <tr>
                    <td>${n.count}</td>
                    <td>${h.method}</td>
                    <td>${h.amount}</td>
                    <td>${h.balance}</td>
                </tr>
            </c:forEach>
        </table>
        <a href="Login"><button>Back</button></a>
    </body>
</html>
