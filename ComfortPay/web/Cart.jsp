<%-- 
    Document   : Cart
    Created on : Nov 16, 2018, 3:19:12 AM
    Author     : Techin
--%>

<%@page import="comfortpay.model.Cart"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart</title>
    </head>
    <body>
        <jsp:include page="Components/Navbar.jsp"/>
        <div class="container text-center" style="padding-top: 130px;">
            <h1>Cart</h1>
            <div class="col-12 row m-0">
                <div class="col-10 mx-auto" >
                    <div class="row m-0 pt-3 pb-3" style="background-color: #53759A;color: #D5C4AD; border: 1px solid black">
                        <div class="col">Product Code</div>
                        <div class="col">Image</div>
                        <div class="col">Product Name</div>
                        <div class="col">Size</div>
                        <div class="col">Quantity</div>
                        <div class="col">REMOVE</div>
                    </div>
                    <c:forEach items="${cart.productLine}" var="pdl">
                        <div class="row m-0 p-1" style="background-color: #ECEEF0">
                            <div class="col "><span class="align-middle">${pdl.product.productcode}</span></div>
                            <div class="col"><img src="images/PictureWebPro/Products/${pdl.product.productcode}.jpg" width="100%"></div>
                            <div class="col"><span class="align-middle">${pdl.product.productname}</span></div>
                            <div class="col"><span class="align-middle">${pdl.size.size}</span></div>
                            <div class="col">
                                <a href="ReduceProduct?productid=${pdl.product.productid}&sizeid=${pdl.size.sizeid}"><button type="button" class="btn btn-info mr-1">-</button></a>
                                ${pdl.quantity}
                                <a href="AddProduct?productid=${pdl.product.productid}&sizeid=${pdl.size.sizeid}"><button type="button" class="btn btn-info ml-1">+</button></a>
                            </div>
                            <div class="col"><a href="RemoveProduct?productid=${pdl.product.productid}&sizeid=${pdl.size.sizeid}"><button type="button" class="btn btn-danger">REMOVE</button></a></div>
                        </div>
                    </c:forEach>
                    <div class="bg-warning p-2 col-12 row m-0">
                        <div class="text-left col">
                            <a href="Home"><button type="button" class="btn btn-dark">HOME</button></a>
                        </div>
                        <div class="col text-right">
                            <a href="Checkform"><button type="button" class="btn btn-success">CHECK BILL</button></a> 
                        </div>

                    </div>

                </div>
                <div class="col-2 pt-2" style="background-color: #53759A;color: #D5C4AD;">
                    <%
                        Cart cart = (Cart) session.getAttribute("cart");
                        if (cart == null) {
                    %>
                    <div>Total OrderPrice 0.0    ฿</div>
                    <div>Total Quantity 0</div>

                    <% } else {%>
                    <div class="col">Total OrderPrice ${cart.totalPrice} ฿</div>
                    <div>Total Quantity ${cart.totalQuantity}</div>

                    <% }%>
                </div>
            </div>
        </div>
    </div>

</body>
</html>
