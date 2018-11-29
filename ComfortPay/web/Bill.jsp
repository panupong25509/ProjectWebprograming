<%-- 
    Document   : Bill
    Created on : Nov 29, 2018, 11:11:11 PM
    Author     : Joknoi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://fonts.googleapis.com/css?family=Press+Start+2P" rel="stylesheet">
        <title>JSP Page</title>
    </head>
    <style>
        #Slip {
            font-family: 'Press Start 2P', cursive;
            font-size: 8px;
        }
    </style>
    <body>
        <jsp:include page="Components/Navbar.jsp"/>
        <div id="Slip" style="padding-top: 150px;">
            <div class="col-6 mx-auto p-5" style="border: 1px solid black;">
                <h3 class="text-center">Bill<br>Comfort Pay</h3>
                <p>Order Code : ${order.ordercode}</p>
                <div class="col-12 row m-0">
                    <div class="col">Product Code</div>
                    <div class="col">Product Name</div>
                    <div class="col">Size</div>
                    <div class="col">Quantity</div>
                    <div class="col">Total Price</div>
                </div>
                <c:forEach items="${orderlist}" var="list">
                    <div class="col-12 row m-0 mt-1">
                        <div class="col">${list.productid.productcode}</div>
                        <div class="col">${list.productid.productname}</div>
                        <div class="col">${list.sizeid.size}</div>
                        <div class="col">${list.quantity}</div>
                        <div class="col">${list.totalprice}</div>
                    </div>
                    <!--<p>${list.productid.productname}</p>-->
                </c:forEach>    
                <div class="text-right pt-5 pr-5">Total OrderPrice : ${order.totalprice}</div>
            </div>

        </div>

    </body>
</html>
