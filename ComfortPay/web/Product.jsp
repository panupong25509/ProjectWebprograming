<%-- 
    Document   : Product
    Created on : Nov 16, 2018, 1:45:44 AM
    Author     : Techin
--%>

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
            <h1>Product</h1>
            <div class="col-12 row m-0">
                <div class="col p-3 text-right">
                    <img src="images/PictureWebPro/adidasMencCloth/${product.productcode}.jpg" width="80%">
                </div>
                <div class="col p-3 text-left">
                    <p>ProductName : ${product.productname}</p>
                    <p>ProductCode : ${product.productcode}</p>
                    <p>ProductType : ${product.producttype}</p>
                    <p>Color : ${product.color}</p>
                    <p>Price : ${product.price}</p>

                    <form action="AddToCart" method="post">
                        <input type="hidden" name="productid" value="${product.productid}"> 
                        <input type="submit" value="ADD">
                    </form>
                </div>
            </div>


        </div>

    </body>
</html>
