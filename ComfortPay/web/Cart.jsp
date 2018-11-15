<%-- 
    Document   : Cart
    Created on : Nov 16, 2018, 3:19:12 AM
    Author     : Techin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart</title>
    </head>
    <body>
        <h1>Cart</h1>
        <p>${productline.product.productname}</p>
        <p>${productline.product}</p>
        <p>${productline.quantity}</p>
        <c:forEach items="${cart.productLine}" var="pdl">
            <p>${pdl.product.productname} ${pdl.quantity}</p>
            
        </c:forEach>
    </body>
</html>
