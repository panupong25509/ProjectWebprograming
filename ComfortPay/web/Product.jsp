<%-- 
    Document   : Product
    Created on : Nov 16, 2018, 1:45:44 AM
    Author     : Techin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product</title>
    </head>
    <body>
        <jsp:include page="Components/Navbar.jsp"/>
        <div class="container text-center" style="padding-top: 130px;">
            <div class="col-8 mx-auto p-3" style="background-color: #53759A;color: #D5C4AD;">

                <h1>Product</h1>
                <div class="col-12 row m-0">
                    <div class="col p-3 text-right">
                        <img src="images/Products/${product.productcode}.jpg" width="100%">

                    </div>
                    <div class="col p-3 text-left">
                        <p>ProductName : ${product.productname}</p>
                        <p>ProductCode : ${product.productcode}</p>
                        <p>ProductType : ${product.producttype}</p>
                        <p>Color : ${product.color}</p>
                        <p>Price : ${product.price} ฿</p>
                        <form action="AddToCart" method="post">
                            <select name="sizeId" class="btn dropdown-toggle">
                                <c:forEach items="${productSize}" var="size">
                                    <option value="${size.sizeid}">${size.size}</option>
                                </c:forEach>  
                            </select>
                            <input type="hidden" name="productid" value="${product.productid}"> 
                            <button type="button submit" class="btn btn-warning ml-5">ADD TO CART</button>
                        </form>
                    </div>
                </div>
            </div>
                            <div class="col-8 mx-auto m-0 p-0"><a href="Home"><button class="btn btn-warning" style="width: 100%">BACK</button></a></div>


        </div>

    </body>
</html>
