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
        <jsp:include page="Components/Navbar.jsp"/>
        <div class="container text-center" style="padding-top: 130px;">
            <h1>Cart</h1>
            <div class="col-12">
                <div class="row m-0 pt-3 pb-3" style="background-color: #ECEEF0; border: 1px solid black">
                        <div class="col">Product Code</div>
                        <div class="col">Product Name</div>
                        <div class="col">Size</div>
                        <div class="col">Quantity</div>
                        <div class="col">REMOVE</div>
                    </div>
                <c:forEach items="${cart.productLine}" var="pdl">
                    <div class="row m-0 p-1" style="background-color: #ECEEF0">
                        <div class="col">${pdl.product.productcode}</div>
                        <div class="col">${pdl.product.productname}</div>
                        <div class="col">${pdl.size.sizeid}</div>
                        <div class="col">
                            <a href="ReduceProduct?productid=${pdl.product.productid}&sizeid=${pdl.size.sizeid}"><button>-</button></a>
                            ${pdl.quantity}
                            <a href="AddProduct?productid=${pdl.product.productid}&sizeid=${pdl.size.sizeid}"><button>+</button></a>
                        </div>
                        <div class="col"><a href="RemoveProduct?productid=${pdl.product.productid}&sizeid=${pdl.size.sizeid}"><button>REMOVE</button></a></div>
                    </div>
                </c:forEach>
            </div>
        </div>

    </body>
</html>
